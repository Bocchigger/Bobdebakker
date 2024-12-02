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
            "cost" to "€ 6,50"
        ),
        "item2" to mutableMapOf (
            "id" to 2,
            "name" to "Broodjeeeeee Gezondo",
            "description" to "Bobje® jungle (Bobjes)",
            "img" to R.drawable.broodjegezond,
            "cost" to "€ 5,95"
        ),
        "item3" to mutableMapOf (
            "id" to 3,
            "name" to "Nog een broodje",
            "description" to "Geen descriptie eigelijk",
            "img" to R.drawable.broodjegezond,
            "cost" to "€ 109,95"
        ),
        "item4" to mutableMapOf (
            "id" to 4,
            "name" to "Liam Broodje",
            "description" to "Liampje® walvis (Liampjes)",
            "img" to R.drawable.broodjeaap,
            "cost" to "€ 109,95"
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
                items["item$i"]?.get("img").toString().toInt(),
                items["item$i"]?.get("name").toString(),
                items["item$i"]?.get("description").toString()
            ))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter



        //winkelwagenCode()

    }

    fun winkelwagenCode() {


        /*items["ItemCount"] = "12"

        val itemCount : Int = items["ItemCount"]!!.toInt()
        for (i in 1..itemCount) {
            println(i)
        }*/

        //itemCountCode()


    }

    private fun itemCountCode() {
        val itemCount = findViewById<TextView>(R.id.itemCount)
        itemCount.text = "1"

        findViewById<ImageButton>(R.id.plusBtn).setOnClickListener {
            changeItemCount(+1)
        }

        findViewById<ImageButton>(R.id.minBtn).setOnClickListener {
            changeItemCount(-1)
            println()
        }
    }
    private fun changeItemCount(count: Int) {
        // Get Id's
        val itemCount = findViewById<TextView>(R.id.itemCount)
        val itemCost = findViewById<TextView>(R.id.itemCost)

        // Set Count
        val count = itemCount.text.toString().toInt() + count

        // Check range
        if (count < 1 || count > 50) return
        // Else go further
        itemCount.text = count.toString()

        // Set Cost
        val cost = count * 6.5

        // Setup Cost Display
        var costString = ""
        for (token in cost.toString()) {
            if (token.toString() == ".") {
                costString += ","
            } else {
                costString += token.toString()
            }
        }
        costString = "€ " + costString + "0"

        // Set Cost Display
        itemCost.text = costString
    }
}