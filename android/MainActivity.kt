package com.bratana.vpn

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Свързване с UI елементите
        val connectButton = findViewById<Button>(R.id.connectButton)
        val serverList = findViewById<Spinner>(R.id.serverList)
        val speedInfo = findViewById<TextView>(R.id.speedInfo)

        // Действие при натискане на бутона CONNECT
        connectButton.setOnClickListener {
            val selectedServer = serverList.selectedItem.toString()
            connectToVPN(selectedServer, speedInfo)
        }
    }

    // Функция за свързване към VPN
    private fun connectToVPN(server: String, speedInfo: TextView) {
        val client = OkHttpClient()
        val url = "http://<your-server-ip>:5000/connect?server=$server"

        val request = Request.Builder()
            .url(url)
            .build()

        Thread {
            try {
                val response: Response = client.newCall(request).execute()
                runOnUiThread {
                    speedInfo.text = response.body?.string()
                }
            } catch (e: IOException) {
                runOnUiThread {
                    speedInfo.text = "Connection failed!"
                }
            }
        }.start()
    }
}
