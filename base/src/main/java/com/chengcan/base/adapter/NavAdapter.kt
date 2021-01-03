package com.chengcan.base.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.chengcan.base.R

class NavAdapter(val data: ArrayList<ItemWrapper>, val fragment: Fragment) :
    RecyclerView.Adapter<NavAdapter.ViewHolder>() {

    val options = navOptions {
        anim {
            enter = R.anim.base_slide_in_right
            exit = R.anim.base_slide_out_left
            popEnter = R.anim.base_slide_in_left
            popExit = R.anim.base_slide_out_right
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val text = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(text)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = holder.view.findViewById<TextView>(android.R.id.text1)
        textView.text = data[position].name
        textView.setOnClickListener {
            findNavController(fragment).navigate(data[position].id, data[position].param, options)
        }
    }

}

data class ItemWrapper(val name: String, val id: Int, var param: Bundle?) {
    constructor(name: String, id: Int) : this(name, id, null)
}