package com.example.phone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            Toast.makeText(context, "Connected to internet", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context, "Not connected to internet", Toast.LENGTH_SHORT).show()
            }
        }
    }
