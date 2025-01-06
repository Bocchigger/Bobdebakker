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
            /* Example:

            "item1" to mutableMapOf (
                "id" to 1,
                "name" to "Name",
                "description" to "Description",
                "img" to R.drawable.broodjeaap,
                "count" to 1,
                "cost" to 0
            )

            */
            "" to mutableMapOf (
                "" to ""
            ) // Add empty key and string to add nothing. This is to avoid any bugs
        )

        val remove = arrayOf(
            /* Example:

            "item1", -- This if you want to delete the normal way
            "additem3" -- This if you want to delete out of the add-map

            */
            "" // Add empty string to remove nothing. This is to avoid any bugs
        )

        findViewById<Button>(R.id.winkelwagenButton).setOnClickListener {
            val intent = Intent(this, Winkelwagen::class.java)

            intent.putExtra("add", HashMap(add))
            intent.putExtra("remove", remove)
            startActivity(intent)
        }
    }
}