package com.example.bobdebakkermainfase1

import android.content.Context
import android.content.Intent
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

        /*when (val type = intent.getStringExtra("type")) {
            "addItem" -> addItem(intent)
            "removeItem" -> removeItem(intent)
            else -> error("There's no type called '$type'")
        }

        when (val type2 = intent.getStringExtra("type2")) {
            "addItem" -> addItem(intent)
            "removeItem" -> removeItem(intent)
            null -> println("Type2 nothing")
            else -> error("There's no type called '$type2'")
        }*/

        intent.getSerializableExtra("add")?.let {
            val add = it as MutableMap<String, MutableMap<String, Any>>
            addItem(add)
        }

        refreshRecycler()
    }

    private fun refreshRecycler() {
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
                i,
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

        calculateTotalCost()
    }

    private fun calculateTotalCost() {
        var totalCost = 0
        for (i in 1..items.count()) {
            val cost = items["item$i"]?.get("cost").toString().toInt() *
                    this.items["item$i"]?.get("count").toString().toInt()
            totalCost += cost
        }
        findViewById<TextView>(R.id.totalCost).text = costToDisplay(totalCost)
    }

    private fun costToDisplay(cost: Int): CharSequence {
        val tokens = cost.toString().toCharArray()
        return when (tokens.size) {
            0 -> "€ 0,00"
            1 -> "€ 0,0" + tokens[0]
            2 -> "€ 0," + tokens[0] + tokens[1]
            3 -> "€ " + tokens[0] + "," + tokens[1] + tokens[2]
            4 -> "€ " + tokens[0] + tokens[1] + "," + tokens[2] + tokens[3]
            5 -> "€ " + tokens[0] + tokens[1] + tokens[2] + "," + tokens[3] + tokens[4]
            6 -> "€ " + tokens[0] + tokens[1] + tokens[2] + tokens[3] + "," + tokens[4] + tokens[5]
            7 -> "€ " + tokens[0] + tokens[1] + tokens[2] + tokens[3] + tokens[4] + "," + tokens[5] + tokens[6]
            else -> "ERR: Size out of range"
        }
    }

    private fun addItem(add: MutableMap<String, MutableMap<String, Any>>) {
        val itemsCount = items.count()
        for (item in add) {
            val id = itemsCount + add[item.key]?.get("id").toString().toInt()
            items["item$id"] = mutableMapOf(
                "id" to id,
                "name" to add[item.key]?.get("name").toString(),
                "description" to add[item.key]?.get("description").toString(),
                "img" to add[item.key]?.get("img").toString().toInt(),
                "count" to add[item.key]?.get("count").toString().toInt(),
                "cost" to add[item.key]?.get("cost").toString().toInt()
            )
        }
    }

    private fun removeItem(intent: Intent) {
        val id = intent.getIntExtra("remover", 0)
        if (id == 0) {
            println("Id is 0")
        } else {
            for (key in items.keys) {
                if (items[key]?.get("id") == id) {
                    items.remove(key)
                    break
                }
            }
        }
    }
}