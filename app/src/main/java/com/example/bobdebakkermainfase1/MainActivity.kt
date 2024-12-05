package com.example.bobdebakkermainfase1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginpagina)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.Bigbroodbutton).setOnClickListener {
            var intent = Intent(this, Brodenpagina::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Bigaccountbutton).setOnClickListener {
            var intent = Intent(this, Loginpagina::class.java)
            startActivity(intent)
        }
    }
}