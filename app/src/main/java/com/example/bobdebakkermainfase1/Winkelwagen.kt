package com.example.bobdebakkermainfase1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Winkelwagen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winkelwagen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemCount = findViewById<TextView>(R.id.itemCount)
        itemCount.text = "1"
        //findViewById<Button>(R.id.plusBtn).setOnClickListener {
            //
        //
        //}

        findViewById<ImageButton>(R.id.plusBtn).setOnClickListener {
            ChangeItemCount(+1)
        }

        findViewById<ImageButton>(R.id.minBtn).setOnClickListener {
            ChangeItemCount(-1)
        }

    }

    fun ChangeItemCount(count: Int) {
        val itemCount = findViewById<TextView>(R.id.itemCount)
        itemCount.text = (itemCount.text.toString().toInt() + count).toString()
    }
}