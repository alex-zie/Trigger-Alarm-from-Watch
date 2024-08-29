package com.alex.triggeralarmapp

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.wearable.Wearable

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button in the layout
        val button: Button = findViewById(R.id.button)

        // Set up the button click listener
        button.setOnClickListener {
            // Send a message to the Wear OS device when button2 is pressed
            sendMessageToWearable("/start-activity", "Start Activity on Wear OS")
        }
    }

    private fun sendMessageToWearable(path: String, message: String) {
        val nodeClient = Wearable.getNodeClient(this)
        val messageClient = Wearable.getMessageClient(this)

        // Get the connected nodes (Wear OS devices)
        nodeClient.connectedNodes.addOnSuccessListener { nodes ->
            if (nodes.isNotEmpty()) {
                // Loop through each connected node and send the message
                for (node in nodes) {
                    messageClient.sendMessage(node.id, path, message.toByteArray())
                        .addOnSuccessListener {
                            // Message sent successfully
                            println("Message sent to ${node.displayName}")
                        }
                        .addOnFailureListener {
                            // Failed to send the message
                            println("Failed to send message to ${node.displayName}")
                        }
                }
            } else {
                println("No connected Wear OS devices found.")
            }
        }.addOnFailureListener {
            println("Failed to retrieve connected nodes.")
        }
    }
}
