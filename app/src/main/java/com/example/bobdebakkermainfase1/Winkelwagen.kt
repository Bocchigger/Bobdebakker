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

class Winkelwagen : AppCompatActivity() {
    private var items : MutableMap<String, MutableMap<String, Any>> = mutableMapOf(
        "item1" to mutableMapOf (
            "id" to 1,
            "name" to "Broodje Kippendij",
            "description" to "Bobje® jungle (Bobjes)",
            "img" to "",
            "cost" to "€ 6,50"
        ),
        "item2" to mutableMapOf (
            "id" to 2,
            "name" to "Broodjeeeeee Gezondo",
            "description" to "Bobje® jungle (Bobjes)",
            "img" to "",
            "cost" to "€ 5,95"
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





        winkelwagenCode()

    }

    fun winkelwagenCode() {


        /*items["ItemCount"] = "12"

        val itemCount : Int = items["ItemCount"]!!.toInt()
        for (i in 1..itemCount) {
            println(i)
        }*/

        //itemCountCode()

        items["item1"]?.set("img", ContextCompat.getDrawable(this, R.drawable.broodjekippendij) as Drawable)
        items["item2"]?.set("img", ContextCompat.getDrawable(this, R.drawable.broodjegezond) as Drawable)

        val itemTitle = findViewById<TextView>(R.id.itemTitle)
        val itemDescription = findViewById<TextView>(R.id.itemDescription)
        val itemImage = findViewById<ImageView>(R.id.itemImage)
        val itemCost = findViewById<TextView>(R.id.itemCost)

        println(items["item2"]?.get("name"))
        itemTitle.text = items["item2"]?.get("name").toString()
        itemDescription.text = items["item2"]?.get("description").toString()
        itemImage.setImageDrawable(items["item2"]?.get("img") as Drawable)
        itemCost.text = items["item2"]?.get("cost").toString()
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