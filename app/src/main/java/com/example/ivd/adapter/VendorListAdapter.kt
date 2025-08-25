package com.example.ivd.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ivd.R
import com.example.ivd.data.VendorListModel

class VendorListAdapter(
    activity: Context?,
) :
    RecyclerView.Adapter<VendorListAdapter.ViewHolder>() {
    val activity = activity

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vendor_list_adapter, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(R.drawable.vendor_image)
        holder.hospitalType.text = "Shyam singh"
    }
    override fun getItemCount(): Int {
        return 10
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.vendor_imageview)
        val hospitalType: TextView = itemView.findViewById(R.id.vendor_textView)
    }

}