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
    private var items : HashMap<String, String> = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winkelwagen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        WinkelwagenCode()

    }

    fun WinkelwagenCode() {


        /*items["ItemCount"] = "12"

        val itemCount : Int = items["ItemCount"]!!.toInt()
        for (i in 1..itemCount) {
            println(i)
        }*/
        itemCountCode()
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