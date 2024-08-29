package com.alex.triggeralarmwear

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.annotation.RequiresApi

class AlarmActivity : Activity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_activity)

        // Initialize Vibrator
        val vibratorManager = getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator

        // Define the vibration pattern and duration
        val vibrationPattern = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)

        // Trigger the vibration
        vibrator.vibrate(vibrationPattern)
    }
}