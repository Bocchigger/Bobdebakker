package com.example.bobdebakkermainfase1

import android.widget.TextView

data class ItemsViewModel(val items: MutableMap<String, MutableMap<String, Any>>, val id: Int, val image: Int, val text: String, val description: String, val count: Int, val cost: Int, var totalCost: TextView)
