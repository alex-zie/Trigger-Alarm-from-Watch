package com.alex.triggeralarmwear

import android.app.Activity
import com.google.android.gms.wearable.Wearable
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.MessageEvent
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.TextView

class MainActivity : Activity(), MessageClient.OnMessageReceivedListener {
    private lateinit var textField : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textField = findViewById(R.id.textView3)

        Log.d("MainActivity", "Watch started!")

        // Register the listener for messages
        Wearable.getMessageClient(this).addListener(this)
    }

    override fun onMessageReceived(messageEvent: MessageEvent) {
        if (messageEvent.path == "/start-activity") {
            Log.d("MainActivity", "Message received!")
            textField.setText("Received")
            val intent = Intent(this, AlarmActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            Wearable.getMessageClient(this).removeListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onResume() {
        super.onResume()
        try {
            Wearable.getMessageClient(this).addListener(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Wearable.getMessageClient(this).removeListener(this)
    }
}
