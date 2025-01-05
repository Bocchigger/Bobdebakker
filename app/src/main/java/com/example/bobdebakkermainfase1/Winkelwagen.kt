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
            "name" to "ik wil kaas",
            "description" to "kassshhh",
            "img" to R.drawable.broodjekippendij,
            "count" to 1,
            "cost" to 199
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

        removeItem(intent.getStringArrayExtra("remove")!!)

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
        val totalCostTxt = findViewById<TextView>(R.id.totalCost)
        val isEmptyTxt = findViewById<TextView>(R.id.nothingHere)
        if (items.isEmpty()) {
            isEmptyTxt.text = "Je winkewagen is leeg..."
            totalCostTxt.text = "-"
            return
        }
        isEmptyTxt.text = ""

        var totalCost = 0
        for (i in 1..items.count()) {
            val cost = items["item$i"]?.get("cost").toString().toInt() *
                    this.items["item$i"]?.get("count").toString().toInt()
            totalCost += cost
        }
        totalCostTxt.text = costToDisplay(totalCost)
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

    private fun removeItem(removes: Array<String>) {
        for (item in removes) {
            if (item.removeRange(3, item.toString().length) == "add") {
                intent.getSerializableExtra("add")?.let {
                    val add = it as MutableMap<String, MutableMap<String, Any>>
                    for (key in items.keys) {
                        val adder = item.removeRange(0, 3)
                        if (items[key]?.get("name") == add[adder]?.get("name") &&
                            items[key]?.get("description") == add[adder]?.get("description") &&
                            items[key]?.get("cost") == add[adder]?.get("cost")) {
                            items.remove(key)
                        }
                    }
                }
            } else {
                items.remove(item)
            }
        }
        if (items.isEmpty()) {
            return
        }
        val keys = mutableSetOf<String>()
        for (key in items.keys) {
            keys.add(key)
        }
        var id = 1
        for (key in keys) {
            val newKey = "item$id"
            items[newKey] = items.remove(key)!!
            items[newKey]?.set("id", id)
            id++
        }
    }
}