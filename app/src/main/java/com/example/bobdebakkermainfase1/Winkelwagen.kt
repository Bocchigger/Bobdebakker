package com.example.bobdebakkermainfase1

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Winkelwagen : AppCompatActivity() {
    private var items : MutableMap<String, MutableMap<String, Any>> = mutableMapOf(
        "item1" to mutableMapOf (
            "id" to 1,
            "name" to "Broodje Kippendij",
            "description" to "Bobje® jungle (Bobjes)",
            "img" to R.drawable.broodjekippendij,
            "count" to 1,
            "cost" to 650
        ),
        "item2" to mutableMapOf (
            "id" to 2,
            "name" to "Broodjeeeeee Gezondo",
            "description" to "Bobje® jungle (Bobjes)",
            "img" to R.drawable.broodjegezond,
            "count" to 1,
            "cost" to 595
        ),
        "item3" to mutableMapOf (
            "id" to 3,
            "name" to "Nog een broodje",
            "description" to "Geen descriptie eigelijk",
            "img" to R.drawable.broodjegezond,
            "count" to 1,
            "cost" to 850
        ),
        "item4" to mutableMapOf (
            "id" to 4,
            "name" to "Liam Broodje",
            "description" to "Liampje® walvis (Liampjes)",
            "img" to R.drawable.broodjeaap,
            "count" to 1,
            "cost" to 10995
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winkelwagen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.itemsList)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..items.count()) {
            data.add(ItemsViewModel(
                items,
                items["item$i"]?.get("img").toString().toInt(),
                items["item$i"]?.get("name").toString(),
                items["item$i"]?.get("description").toString(),
                items["item$i"]?.get("count").toString().toInt(),
                items["item$i"]?.get("cost").toString().toInt(),
                findViewById(R.id.totalCost)
            ))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}