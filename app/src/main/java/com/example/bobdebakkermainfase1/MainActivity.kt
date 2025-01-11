package com.example.bobdebakkermainfase1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val add: MutableMap<String, MutableMap<String, Any>> = mutableMapOf (
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

    private val remove = arrayOf(
        /* Example:

        "item1", -- This if you want to delete the normal way
        "additem3" -- This if you want to delete out of the add-map

        */
        "" // Add empty string to remove nothing. This is to avoid any bugs
    )

    var items: MutableMap<String, MutableMap<String, Any>> = mutableMapOf(

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        intent.getSerializableExtra("winkelwagen")?.let {
            val newItems = it as? MutableMap<String, MutableMap<String, Any>>
            if (newItems != null) {
                items = newItems
            }
        }
        println(items)



        findViewById<ImageButton>(R.id.carpaccio).setOnClickListener {
            addItem("Broodje Carpaccio", "Bobje® vitaal", R.drawable.broodjecarpaccio, 725)
        }

        findViewById<ImageButton>(R.id.heelgezondtrust).setOnClickListener {
            addItem("Broodje Gezond", "Bobje® jungle", R.drawable.broodjegezond, 595)
        }

        findViewById<Button>(R.id.winkelwagenButton).setOnClickListener {
            val intent = Intent(this, Winkelwagen::class.java)
            intent.putExtra("winkelwagen", HashMap(items))
            intent.putExtra("add", HashMap(add))
            intent.putExtra("remove", remove)
            startActivity(intent)
        }
    }

    private fun addItem(name: String, description: String, img: Int, cost: Int) {
        var id = add.count() + 1
        if (add[""]?.get("") == "") {
            add.remove("")
            id = 1
        }
        val key = "item$id"
        val count = add.values.count {
            it["name"] == name
        } + items.values.count {
            it["name"] == name
        }
        var itemCount: Int? = null
        if (items.isNotEmpty()) {
            var addEntry = 0
            if (add.isNotEmpty()) {
                addEntry = add[
                    add.entries.find {
                        it.value["name"] == name
                    }?.key
                ]?.get("count").toString().toInt()
            }
            itemCount = addEntry +
                    items[
                        items.entries.find {
                            it.value["name"] == name
                        }?.key
                    ]?.get("count").toString().toInt()
        }
        if (count > 0) {
            val entry = items.entries.find { it.value["name"] == name }
            if (entry == null) {
                val newEntry = add.entries.find { it.value["name"] == name }
                if (newEntry == null) {
                    error("The two entries are both null.")
                } else {
                    newEntry.value["count"] = count + 1
                }
            } else {
                if (itemCount != null) {
                    if (!(itemCount < 0 || itemCount > 49)) {
                        entry.value["count"] = itemCount + 1
                    } else {
                        entry.value["count"] = itemCount
                    }
                }
            }
        } else {
            add[key] = mutableMapOf(
                "id" to id,
                "name" to name,
                "description" to description,
                "img" to img,
                "count" to count + 1,
                "cost" to cost
            )
        }
        Toast.makeText(this, "$name toegevoegd aan winkelwagen", Toast.LENGTH_SHORT).show()
    }
}