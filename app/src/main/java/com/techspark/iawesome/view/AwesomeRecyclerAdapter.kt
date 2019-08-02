package com.techspark.iawesome.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techspark.iawesome.R
import com.techspark.iawesome.database.AwesomeModel
import kotlinx.android.synthetic.main.item_awesome.view.*

class AwesomeRecyclerAdapter(private var items: List<AwesomeModel>) : RecyclerView.Adapter<AwesomeRecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_awesome,parent,false))
    }

    override fun getItemCount(): Int {

        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.item_awesome_text.text = items[position].date + "----"+ items[position].time
    }

}