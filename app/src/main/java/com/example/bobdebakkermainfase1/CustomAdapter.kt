package com.example.bobdebakkermainfase1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
        holder.description.text = ItemsViewModel.description
        holder.cost.text = ItemsViewModel.cost

        holder.minBtn.setOnClickListener {
            changeItemCount(-1, holder)
        }

        holder.plusBtn.setOnClickListener {
            changeItemCount(+1, holder)
        }
    }

    private fun changeItemCount(adderCount: Int, holder: ViewHolder) {
        // Set Count
        val count = holder.count.text.toString().toInt() + adderCount

        // Check range
        if (count < 1 || count > 50) return
        // Else go further
        holder.count.text = count.toString()

        changeItemCost(count, adderCount, holder)
    }

    private fun changeItemCost(count: Int, adderCount: Int, holder: ViewHolder) {
        val baseCost = decryptCost(holder.cost.text.toString()) / (count - adderCount)
        //

        // Setup Cost Display

        // Set Cost Display

    }

    private fun decryptCost(costString: String): Double {
        var cost: String = ""
        for (token in costString) {
            if (token.toString() == "€") {
                continue
            } else if (token.toString() == " ") {
                continue
            } else if (token.toString() == ",") {
                cost += "."
            } else {
                cost += token.toString()
            }
        }
        return cost.toDouble()
    }
    private fun encryptCost(cost: Double): String {
        var costString = ""
        var idx = 0;
        for (token in cost.toString()) {
            costString += if (token.toString() == ".") {
                ","
            } else {
                token.toString()
            }
            idx++
        }
        costString = if (idx > 3) {
            "€ $costString"
        } else {
            "€ " + costString + "0"
        }
        return costString;
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        val textView: TextView = itemView.findViewById(R.id.itemTitle)
        val description: TextView = itemView.findViewById(R.id.itemDescription)
        val cost: TextView = itemView.findViewById(R.id.itemCost)
        val minBtn: ImageButton = itemView.findViewById(R.id.minBtn)
        val plusBtn: ImageButton = itemView.findViewById(R.id.plusBtn)
        val count: TextView = itemView.findViewById(R.id.itemCount)
    }
}
