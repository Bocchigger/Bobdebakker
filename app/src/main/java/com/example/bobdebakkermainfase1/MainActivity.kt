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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val add = mutableMapOf (
            "item1" to mutableMapOf (
                "id" to 1,
                "name" to "ya",
                "description" to "yayaya",
                "img" to R.drawable.broodjeaap,
                "count" to 1,
                "cost" to 95
            ),
            "item2" to mutableMapOf (
                "id" to 2,
                "name" to "yaaa",
                "description" to "yayayaada",
                "img" to R.drawable.broodjeaap,
                "count" to 1,
                "cost" to 96
            ),
            "item3" to mutableMapOf (
                "id" to 3,
                "name" to "ahh",
                "description" to "ahhhhh",
                "img" to R.drawable.broodjeaap,
                "count" to 1,
                "cost" to 97
            ),
            "item4" to mutableMapOf (
                "id" to 4,
                "name" to "ikki",
                "description" to "ikikik",
                "img" to R.drawable.broodjegezond,
                "count" to 1,
                "cost" to 98
            )
        )

        val remove = arrayOf(
            "item1",
            "additem3"
        )

        findViewById<Button>(R.id.winkelwagenBtn).setOnClickListener {
            val intent = Intent(this, Winkelwagen::class.java)

            intent.putExtra("add", HashMap(add))
            intent.putExtra("remove", remove)
            startActivity(intent)
        }
    }
}