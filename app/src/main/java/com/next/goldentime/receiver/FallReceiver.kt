package com.next.goldentime.receiver

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.SystemClock
import android.widget.Chronometer
import com.next.goldentime.App
import com.next.goldentime.ui.SOSActivity
import com.next.goldentime.usecase.patient.SOSType
import java.util.concurrent.LinkedBlockingQueue
import kotlin.math.sqrt

private enum class State {
    NORMAL, FALLING, COLLISION
}

private const val MIN_THRESHOLD = 0.6
private const val COLLISION_THRESHOLD = 3.0

private const val FALLEN_TIME = 200

class FallReceiver : SensorEventListener {
    private var state = State.NORMAL

    private var clock: Chronometer = Chronometer(App.context)
    private val accelerationQueue = LinkedBlockingQueue<Double>(10)

    private var x = 0f
    private var y = 0f
    private var z = 0f
    private var g = 0f

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values

        val x = values[0].toDouble()
        val y = values[1].toDouble()
        val z = values[2].toDouble()

        val acceleration =
            sqrt(x * x + y * y + z * z / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH))
        accelerationQueue.offer(acceleration)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }

        x = event.values[0]
        y = event.values[1]
        z = event.values[2]

        val rootSum: Double = sqrt((x * x + y * y + z * z).toDouble())
        g = (rootSum / 9.8).toFloat()

        evaluate()
    }

    private fun evaluate() {
        if (g < MIN_THRESHOLD) {
            clock.base = SystemClock.elapsedRealtime()
            clock.start()

            state = State.FALLING
        } else if (g >= COLLISION_THRESHOLD && state == State.FALLING) {
            val elapsedMillis = SystemClock.elapsedRealtime() - clock.base

            if (elapsedMillis > FALLEN_TIME) {
                state = State.COLLISION

                val intent = Intent(App.context, SOSActivity::class.java)
                intent.putExtra("sosType", SOSType.FALL)
                App.context.startActivity(intent)
            }

            initialize()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    private fun initialize() {
        state = State.NORMAL

        clock.stop()
        clock.base = SystemClock.elapsedRealtime()
    }
}