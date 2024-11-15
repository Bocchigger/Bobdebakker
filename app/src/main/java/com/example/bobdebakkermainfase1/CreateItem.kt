package com.example.bobdebakkermainfase1

import android.graphics.drawable.Drawable
import androidx.constraintlayout.widget.ConstraintLayout

class CreateItem(name: String, description: String, image: android.graphics.drawable.Drawable, cost: String) {
    val item = findViewById<ConstraintLayout>(R.id.item1)
    // Dit fixen heel raar dit

    val parent = originalView.parent as ViewGroup // De container waarin de view zit
    val inflater = LayoutInflater.from(context)

    // Clone van de originele layout maken
    val clonedView = inflater.inflate(R.layout.jouw_layout, parent, false)

// Voeg de gekloonde view toe aan de container
    parent.addView(clonedView)
}