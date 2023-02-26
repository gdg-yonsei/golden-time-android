package com.next.goldentime.receiver

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.*
import android.speech.tts.TextToSpeech
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import android.view.View
import android.widget.*
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.next.goldentime.R
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

class FallDetectorTemp : SensorEventListener {
    var textToSpeech: TextToSpeech? = null
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
    private var currentX = 0.0

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
    private var stateView: TextView? = null
    private var textViewUser: TextView? = null
    private var textViewContact: TextView? = null
    private var textViewNumber: TextView? = null

    // Calculations for accelerometor graph display
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
    // ACCELEROMETER watcher
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

        // won't need
        stateView!!.text = "State: $phone"
        Log.d("TAG", "State Changed!")

        //check state
        states()
    }

    fun states() {
        // need help, pop up dialog and wait [add in wait]
        if (phone == State.help && !dialog) {
            /*
            //Open app and alert dialog even when app is not opened
            if (!appInForeground()) {
                Log.d("TAG", "App to Foreground")
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.action = Intent.ACTION_MAIN
                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                startActivity(intent)
            }
            * */
            Log.d("TAG", "FALL DETECTED")
            if (timeout != null) {
                timeout!!.cancel()
            }
            // Start new timer to count down the fall
            timeout = object : CountDownTimer(TIME_OUT.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    // alert111.setMessage(emergency + "within " + millisUntilFinished / 1000 + " seconds.")
                }
                //If times out without response then prompt emergency contact message
                override fun onFinish() {
                    // alert111.dismiss()
                    if (responded == false) {
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

}