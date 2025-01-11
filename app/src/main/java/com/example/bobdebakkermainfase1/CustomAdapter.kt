package com.example.bobdebakkermainfase1

import android.content.ClipData.Item
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
        holder.cost.text = costToDisplay(ItemsViewModel.cost * ItemsViewModel.count)
        holder.count.text = ItemsViewModel.count.toString()

        holder.minBtn.setOnClickListener {
            changeItemCount(-1, holder, ItemsViewModel)
        }

        holder.plusBtn.setOnClickListener {
            changeItemCount(+1, holder, ItemsViewModel)
        }
    }

    private fun changeItemCount(adderCount: Int, holder: ViewHolder, ItemsViewModel: ItemsViewModel) {
        // Set Count
        val id = ItemsViewModel.id
        val item = ItemsViewModel.items["item$id"]
        val count = item?.get("count").toString().toInt() + adderCount

        // Check range
        if (count < 1 || count > 50) return
        // Else go further

        holder.count.text = count.toString()


        item?.set("count", count)
        println(ItemsViewModel)
        changeItemCost(count, holder, ItemsViewModel)
    }

    private fun changeItemCost(count: Int, holder: ViewHolder, ItemsViewModel: ItemsViewModel) {
        val newCost = ItemsViewModel.cost * count

        holder.cost.text = costToDisplay(newCost)

        var totalCost = 0
        for (i in 1..ItemsViewModel.items.count()) {
            val cost = ItemsViewModel.items["item$i"]?.get("cost").toString().toInt() *
                    ItemsViewModel.items["item$i"]?.get("count").toString().toInt()
            totalCost += cost
        }
        ItemsViewModel.totalCost.text = costToDisplay(totalCost)
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
