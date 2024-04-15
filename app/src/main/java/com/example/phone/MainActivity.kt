package com.example.phone

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mixpanel.android.mpmetrics.MixpanelAPI

class MainActivity : AppCompatActivity() {

    private lateinit var connectionReceiver: connectionReceiver
    private lateinit var intentFilter: IntentFilter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val phoneNumberEditText = findViewById<EditText>(R.id.phoneNumberEditText)
        val dialButton = findViewById<Button>(R.id.dialButton)
        val oneButton = findViewById<Button>(R.id.oneButton)
        val twoButton = findViewById<Button>(R.id.twoButton)
        val threeButton = findViewById<Button>(R.id.threeButton)
        val fourButton = findViewById<Button>(R.id.fourButton)
        val fiveButton = findViewById<Button>(R.id.fiveButton)
        val sixButton = findViewById<Button>(R.id.sixButton)
        val sevenButton = findViewById<Button>(R.id.sevenButton)
        val eightButton = findViewById<Button>(R.id.eightButton)
        val nineButton = findViewById<Button>(R.id.nineButton)
        val starButton = findViewById<Button>(R.id.starButton)
        val zeroButton = findViewById<Button>(R.id.zeroButton)
        val hashButton = findViewById<Button>(R.id.hashButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        dialButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            if (!phoneNumber.isEmpty()) {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        CALL_PERMISSION_REQUEST_CODE
                    )
                } else {
                    dialPhoneNumber(phoneNumber)
                }
            }
        }
        oneButton.setOnClickListener { phoneNumberEditText.append("1") }
        twoButton.setOnClickListener { phoneNumberEditText.append("2") }
        threeButton.setOnClickListener { phoneNumberEditText.append("3") }
        fourButton.setOnClickListener { phoneNumberEditText.append("4") }
        fiveButton.setOnClickListener { phoneNumberEditText.append("5") }
        sixButton.setOnClickListener { phoneNumberEditText.append("6") }
        sevenButton.setOnClickListener { phoneNumberEditText.append("7") }
        eightButton.setOnClickListener { phoneNumberEditText.append("8") }
        nineButton.setOnClickListener { phoneNumberEditText.append("9") }
        starButton.setOnClickListener { phoneNumberEditText.append("*") }
        zeroButton.setOnClickListener { phoneNumberEditText.append("0") }
        hashButton.setOnClickListener { phoneNumberEditText.append("#") }
        cancelButton.setOnClickListener { phoneNumberEditText.setText("") }

        mixpanel = MixpanelAPI.getInstance(this, MIXPANEL_TOKEN, false)

        connectionReceiver = connectionReceiver()
        intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(connectionReceiver, intentFilter)
    }

    private fun registerReceiver(
        connectionReceiver: connectionReceiver,
        intentFilter: IntentFilter
    ) {

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectionReceiver)
    }

    private fun unregisterReceiver(connectionReceiver: connectionReceiver) {

    }

    private val MIXPANEL_TOKEN ="083db768315e86ca3bab427cd99940ec"
    private lateinit var mixpanel : MixpanelAPI



    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val phoneNumberEditText = findViewById<EditText>(R.id.phoneNumberEditText)
                val phoneNumber = phoneNumberEditText.text.toString()
                dialPhoneNumber(phoneNumber)
            }
        }
    }

    companion object {
        private const val CALL_PERMISSION_REQUEST_CODE = 101
    }


    }


