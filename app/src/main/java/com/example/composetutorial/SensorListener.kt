package com.example.composetutorial

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorListener (context: Context) : SensorEventListener {
    private val GYRO_CHANNEL="gyro_channel"
    var sensorManager: SensorManager

    lateinit var notificationManager: NotificationManager

    private var gyroscopeSensor: Sensor? = null

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        gyroscopeSensor?.let {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        notificationManager = NotificationManager(context)
        notificationManager.createNotificationChannel(GYRO_CHANNEL)

    }

    fun startSensorListener() {
        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }


    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (event.values[1] > 0 ) {
                notificationManager.createGyroscopeNotification(GYRO_CHANNEL)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}