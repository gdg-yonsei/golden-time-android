package com.next.goldentime.presentation.profile

import android.Manifest
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.*
import android.speech.tts.TextToSpeech
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.io.IOException
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

class MainActivity : AppCompatActivity(), SensorEventListener {
    var textToSpeech: TextToSpeech? = null
    private var userName: String? = null
    private var contactName: String? = null
    private var number: String? = null
    private var message: String? = null
    private var clock: Chronometer? = null
    private var mySensor: Sensor? = null
    private var SM: SensorManager? = null
    private var series: LineGraphSeries<DataPoint>? = null
    private val limit: LineGraphSeries<DataPoint>? = null
    private var liveChartExecutor: ThreadPoolExecutor? = null
    private val accelerationQueue = LinkedBlockingQueue<Double>(10)
    var context: Context = this
    var dialog = false
    private var alert111: AlertDialog? = null
    private var responded = false

    //add enum for states
    private enum class State {
        normal, falling, crash, still, help
    }

    private val MIN_THRESHOLD = 0.6
    private val MAX_THRESHOLD = 1.1
    private val COLLISION_THRESHOLD = 3.0

    //min time to fall
    private val FALLEN = 200

    //min time still to show dialog
    private val HELP_ME = 2500
    private val TIME_OUT = 30000
    private var x = 0f
    private var y = 0f
    private var z = 0f
    private var g = 0f
    private val emergency = "Fall Detected. Do you need help? We will contact your emergency person if you do not respond "
    private var phone = State.normal
    var timeout: CountDownTimer? = null
    private val xView: TextView? = null
    private val yView: TextView? = null
    private val zView: TextView? = null
    private val gView: TextView? = null
    private val milliView: TextView? = null
    private var stateView: TextView? = null
    private var textViewUser: TextView? = null
    private var textViewContact: TextView? = null
    private var textViewNumber: TextView? = null
    var editTextUser: EditText? = null
    var editTextContact: EditText? = null
    var editTextNumber: EditText? = null

    //Set up sensors and text views
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Contact details text view
        val editBtn = findViewById<View>(R.id.editBtn) as Button
        val state = findViewById<View>(R.id.buttonState) as Button
        textViewUser = findViewById(R.id.textViewUser)
        textViewContact = findViewById(R.id.textViewContact)
        textViewNumber = findViewById(R.id.textViewNumber)
        state.setOnClickListener {
            phone = State.help
            Log.d("TAG", "State Changed to HELP")
        }
        editBtn.setOnClickListener { EditDialog() }


        //Text to speech implementation
        textToSpeech = TextToSpeech(this@MainActivity) { status -> //if there is no error then set the language
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.language = Locale.ENGLISH
                textToSpeech!!.setSpeechRate(0.80.toFloat())
            }
        }

        //Create sensor manager
        SM = getSystemService(SENSOR_SERVICE) as SensorManager

        //Accelerometer graph set up
        val graph = findViewById<View>(R.id.graph) as GraphView
        series = LineGraphSeries()
        series!!.color = Color.rgb(57, 192, 186)
        graph.addSeries(series)
        graph.setBackgroundColor(Color.TRANSPARENT)
        graph.titleColor = Color.WHITE
        graph.viewport.isScalable = true
        graph.gridLabelRenderer.gridColor = Color.DKGRAY
        graph.gridLabelRenderer.isHorizontalLabelsVisible = false
        graph.gridLabelRenderer.isVerticalLabelsVisible = false
        // activate horizontal scrolling
        graph.viewport.isScrollable = true
        // activate horizontal and vertical zooming and scrolling
        graph.viewport.setScalableY(true)
        // activate vertical scrolling
        graph.viewport.setScrollableY(true)
        // To set a fixed manual viewport use this:
        // set manual X bounds
        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(0.5)
        graph.viewport.setMaxX(6.5)
        // set manual Y bounds
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(3.0)
        currentX = 0.0
        // Start chart thread
        liveChartExecutor = Executors.newFixedThreadPool(1) as ThreadPoolExecutor
        if (liveChartExecutor != null) liveChartExecutor!!.execute(AccelerationChart(AccelerationChartHandler()))


        //Accelerometer Sensor
        mySensor = SM!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        //Register Sensor Listener
        SM!!.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL)

        //Assign TextViews for phone sate and timer
        stateView = findViewById(R.id.stateView)
        clock = findViewById(R.id.clock)
        clock.setFormat("Time since freefall: %s")

        //Load saved emergency contact details data
        loadData()
        updateViews()
    }

    fun checkPermission(permission: String?): Boolean {
        val check = ContextCompat.checkSelfPermission(this, permission!!)
        return check == PackageManager.PERMISSION_GRANTED
    }

    //Edit contact details
    fun EditDialog() {
        val builderD = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.layout_dialog, null)
        builderD.setView(view)
                .setTitle("Edit Details")
                .setNegativeButton("Cancel") { dialog, which -> } //Get text from edit text in dialog into display textview
                .setPositiveButton("Save") { dialog, which ->
                    textViewUser!!.text = editTextUser!!.text.toString()
                    textViewContact!!.text = editTextContact!!.text.toString()
                    textViewNumber!!.text = editTextNumber!!.text.toString()
                    saveData()
                }
        //Set up editTexts
        editTextUser = view.findViewById(R.id.editTextUser)
        editTextContact = view.findViewById(R.id.editTextContact)
        editTextNumber = view.findViewById(R.id.editTextNumber)
        //When edit button pressed, preset edittext into current details
        editTextUser.setText(textViewUser!!.text.toString())
        editTextContact.setText(textViewContact!!.text.toString())
        editTextNumber.setText(textViewNumber!!.text.toString())
        builderD.create()
        builderD.show()
    }

    //Saving emergency contact details even when app is closed using shared preferences
    fun saveData() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER, textViewUser!!.text.toString())
        editor.putString(CONTACT, textViewContact!!.text.toString())
        editor.putString(NUMBER, textViewNumber!!.text.toString())
        editor.apply()
        Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show()
    }

    //Loading shared preferences
    fun loadData() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        userName = sharedPreferences.getString(USER, "")
        contactName = sharedPreferences.getString(CONTACT, "")
        number = sharedPreferences.getString(NUMBER, "")
    }

    //Update from loaded values
    fun updateViews() {
        textViewUser!!.text = userName
        textViewContact!!.text = contactName
        textViewNumber!!.text = number
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
        //get values
        val rootSum: Double
        x = event.values[0]
        y = event.values[1]
        z = event.values[2]
        rootSum = Math.sqrt((x * x + y * y + z * z).toDouble())
        g = (rootSum / 9.8).toFloat()
        Log.d("TAG", "Gravity: $g")

        //won't need
        stateView!!.text = "State: $phone"
        Log.d("TAG", "State Changed!")

        //check state
        states()
    }

    private fun appInForeground(): Boolean {
        val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val runningAppProcesses = activityManager.runningAppProcesses ?: return false
        for (runningAppProcess in runningAppProcesses) {
            if (runningAppProcess.processName == context.packageName &&
                    runningAppProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true
            }
        }
        return false
    }

    fun states() {
        Log.d("TAG", "called 'states'")

        // need help, pop up dialog and wait [add in wait]
        if (phone == State.help && !dialog) {
            //Open app and alert dialog even when app is not opened
            if (!appInForeground()) {
                Log.d("TAG", "App to Foreground")
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.action = Intent.ACTION_MAIN
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                startActivity(intent)
            }
            val builder1 = AlertDialog.Builder(context)
            //When fall is detected, display alert dialog and speech to text emergency message
            builder1.setTitle("Fall Detected!")
            builder1.setMessage(emergency)
            Log.d("TAG", "FALL DETECTED")
            if (!textToSpeech!!.isSpeaking) {
                textToSpeech!!.speak(emergency, TextToSpeech.QUEUE_FLUSH, null)
            }
            builder1.setCancelable(false)
            //If needs help prompt emergency contact message
            builder1.setPositiveButton(
                    "Yes, get help immediately"
            ) { dialog, which ->
                responded = true
                EmergencyContact()
                dialog.cancel()
            }
            //Apology message on false alarms
            builder1.setNeutralButton(
                    "I fell but I'm okay"
            ) { dialog, which -> //alert111.cancel();
                textToSpeech!!.speak("You should make sure you're okay.", TextToSpeech.QUEUE_FLUSH, null)
                responded = true
                dialog.cancel()
                Initialise()
            }
            //Safety reassurance message on minor falls
            builder1.setNegativeButton(
                    "No, I haven't fallen"
            ) { dialog, id ->
                textToSpeech!!.speak("I'm sorry. False alarm!", TextToSpeech.QUEUE_FLUSH, null)
                responded = true
                dialog.cancel()
                Initialise()
            }
            alert111 = builder1.create()
            alert111.show()

            //Set dialog text sizes
            val no = alert111.getButton(DialogInterface.BUTTON_NEGATIVE)
            no.textSize = 15f
            val ok = alert111.getButton(DialogInterface.BUTTON_NEUTRAL)
            ok.textSize = 15f
            val yes = alert111.getButton(DialogInterface.BUTTON_POSITIVE)
            yes.textSize = 15f
            val fallView = alert111.findViewById<TextView>(android.R.id.message)
            fallView.textSize = 20f
            if (timeout != null) {
                timeout!!.cancel()
            }
            timeout = object : CountDownTimer(TIME_OUT.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    alert111.setMessage(emergency + "within " + millisUntilFinished / 1000 + " seconds.")
                }

                //If times out without response then prompt emergency contact message
                override fun onFinish() {
                    alert111.dismiss()
                    if (responded == false) {
                        EmergencyContact()
                        responded = false
                    }
                }
            }.start()
            dialog = true
        } else if (g < MIN_THRESHOLD) {
            when (phone) {
                State.normal -> {
                    //phone started falling so start clock
                    clock!!.base = SystemClock.elapsedRealtime()
                    clock!!.start()
                    phone = State.falling
                }
            }
        } else if (g >= COLLISION_THRESHOLD && phone == State.falling) {
            Log.d("TAG", "Inside COLLISION")
            val elapsedMillis = SystemClock.elapsedRealtime() - clock!!.base
            //if fallen for long enough, classed as collision, continue
            phone = if (elapsedMillis > FALLEN) State.crash else {
                //not a fall
                Initialise()
                return
            }
        } else if (g >= MIN_THRESHOLD && g <= MAX_THRESHOLD) {
            Log.d("TAG", "Inside STILL")
            when (phone) {
                State.crash -> {
                    //start new timer
                    clock!!.base = SystemClock.elapsedRealtime()
                    phone = State.still
                }
                State.still -> {
                    // get help
                    val elapsedMillis = SystemClock.elapsedRealtime() - clock!!.base
                    if (elapsedMillis > HELP_ME) {
                        phone = State.help
                    }
                }
                State.falling ->                     //low impact, probably a drop
                    Initialise()
            }
        }
    }

    //reset
    fun Initialise() {
        clock!!.stop()
        clock!!.base = SystemClock.elapsedRealtime()
        phone = State.normal
        dialog = false
    }

    //Gathers location of fall and display emergency contact message to specified contact
    fun EmergencyContact() {
        // Set up location access and get permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //TODO: Consider calling
            // public void requestPermissions(@NonNull String[] permissions, int requestCode)
            // here to request the missing permissions, and then overriding
            // public void onRequesetPermissionResult(int requestCode, String[] permissions,
            //                                            int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10f, Listener())
        // Have another for GPS provider just in case.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, Listener())
        // Try to request the location immediately
        var location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        if (location != null) {
            handleLatLng(location.latitude, location.longitude)
        }
        //Set emergency message when location or wifi is turned off
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            message = "$userName has fallen and requires assistance"
            Toast.makeText(applicationContext,
                    "Allow Location permission and turn on High Accuracy setting to send address to contact.",
                    Toast.LENGTH_LONG).show()
        } else {
            message = """$userName has fallen and requires assistance at
${getAddress(location!!.latitude, location.longitude)}"""
            Toast.makeText(applicationContext,
                    "Trying to obtain GPS coordinates. Make sure you have location services on. On High Accuracy",
                    Toast.LENGTH_SHORT).show()
        }
        //Display emergency contact alert dialog
        textToSpeech!!.speak("Contacting help..", TextToSpeech.QUEUE_FLUSH, null)
        textToSpeech!!.speak("Contacting help..", TextToSpeech.QUEUE_FLUSH, null)
        contactName = textViewContact!!.text.toString()
        contactName = textViewContact!!.text.toString()
        userName = textViewUser!!.text.toString()
        val builder3 = AlertDialog.Builder(context)
                .setTitle("Sending Message to $contactName")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(
                        "Thank you"
                ) { dialog, id ->
                    dialog.cancel()
                    Initialise()
                }
        val contact = builder3.create()
        contact.show()
        val yes = contact.getButton(DialogInterface.BUTTON_POSITIVE)
        yes.textSize = 15f
        val fallView = contact.findViewById<TextView>(android.R.id.message)
        fallView.textSize = 20f
    }

    //Alternative display emergency contact message to specified contact when location permission denied
    fun EmergencyNoLocation() {
        textToSpeech!!.speak("Contacting help..", TextToSpeech.QUEUE_FLUSH, null)
        textToSpeech!!.speak("Contacting help..", TextToSpeech.QUEUE_FLUSH, null)
        contactName = textViewContact!!.text.toString()
        contactName = textViewContact!!.text.toString()
        userName = textViewUser!!.text.toString()
        message = "$userName has fallen and requires assistance"
        Toast.makeText(applicationContext,
                "Allow Location permission and turn on High Accuracy setting to send address to contact.",
                Toast.LENGTH_LONG).show()
        val builder3 = AlertDialog.Builder(context)
                .setTitle("Sending Message to $contactName")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(
                        "Thank you"
                ) { dialog, id ->
                    dialog.cancel()
                    Initialise()
                }
        val contact = builder3.create()
        contact.show()
        val yes = contact.getButton(DialogInterface.BUTTON_POSITIVE)
        yes.textSize = 15f
        val fallView = contact.findViewById<TextView>(android.R.id.message)
        fallView.textSize = 20f
    }

    fun handleLatLng(latitude: Double, longitude: Double) {
        Log.v("TAG", "($latitude,$longitude)")
    }

    //Reverse geocoding to obtain current address
    private fun getAddress(latitude: Double, longitude: Double): String {
        var address = ""
        var city = ""
        val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            address = addresses[0].getAddressLine(0)
            city = addresses[0].locality
            Log.d("TAG", "Complete Address: $addresses")
            Log.d("TAG", "Address: $address")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return address
    }

    inner class Listener : AppCompatActivity(), LocationListener {
        override fun onLocationChanged(location: Location) {
            val lon = location.longitude
            val lat = location.latitude
            handleLatLng(lat, lon)
        }

        override fun onProviderDisabled(provider: String) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    //Location permission request
    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity,
                            "Location Access Granted",
                            Toast.LENGTH_LONG).show()
                    EmergencyContact()
                } else {
                    Toast.makeText(this@MainActivity,
                            "Location Access Denied",
                            Toast.LENGTH_LONG).show()
                    EmergencyNoLocation()
                }
                return
            }
        }
    }

    //Calculations for accelerometor graph display
    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        // Movement
        val x = values[0].toDouble()
        val y = values[1].toDouble()
        val z = values[2].toDouble()
        val accelerationSquareRoot = x * x + y * y + z * z / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        val acceleration = Math.sqrt(accelerationSquareRoot)
        accelerationQueue.offer(acceleration)
    }

    //Updates accelerometer graph
    private inner class AccelerationChartHandler : Handler() {
        override fun handleMessage(msg: Message) {
            var accelerationY = 0.0
            if (msg.data.getString("ACCELERATION_VALUE") != null && msg.data.getString("ACCELERATION_VALUE") != "null") {
                accelerationY = msg.data.getString("ACCELERATION_VALUE")!!.toDouble()
            }
            series!!.appendData(DataPoint(currentX, accelerationY), true, 10)
            currentX = currentX + 1
        }
    }

    //Live implementation of graph
    private inner class AccelerationChart(private val handler: Handler) : Runnable {
        private val drawChart = true
        override fun run() {
            while (drawChart) {
                var accelerationY: Double?
                accelerationY = try {
                    Thread.sleep(300) // Speed up the X axis
                    accelerationQueue.poll()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    continue
                }
                if (accelerationY == null) continue
                val msgObj = handler.obtainMessage()
                val b = Bundle()
                b.putString("ACCELERATION_VALUE", accelerationY.toString())
                msgObj.data = b
                handler.sendMessage(msgObj)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        //not in use
    }

    companion object {
        const val MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        const val SHARED_PREFS = "sharedPrefs"
        const val USER = "User"
        const val CONTACT = "Contact"
        const val NUMBER = "Number"
        private var currentX = 0.0
    }
}