package com.example.wishlist

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter: RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    private val items = mutableListOf<WishlistItem>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val nameTextView: TextView
            val namePRiceView: TextView
            val urlTextView: TextView

            init{
                nameTextView = itemView.findViewById(R.id.textView4)
                namePRiceView = itemView.findViewById(R.id.textView5)
                urlTextView = itemView.findViewById(R.id.textView6)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.namePRiceView.text = "$${item.price}"
        holder.urlTextView.text = item.url

        holder.itemView.setOnLongClickListener {
            // Prompt the user for confirmation to delete the item
            val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setTitle("Delete Item")
            alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                // Remove the item from the dataset
                items.removeAt(position)
                // Notify the adapter of the item removal
                notifyItemRemoved(position)
            }
            alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialogBuilder.show()

            true // Consume the long-click event
        }

        holder.urlTextView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: WishlistItem) {
        items.add(item)
        notifyDataSetChanged() // Notify the adapter that the data set has changed
        Log.d("WishlistAdapter", "Item added: ${item.name}")
    }

}

