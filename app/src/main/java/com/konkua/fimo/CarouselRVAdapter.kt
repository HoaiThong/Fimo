package com.konkua.fimo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CarouselRVAdapter(private val roboList: ArrayList<Robocash>) :
    RecyclerView.Adapter<CarouselRVAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.textview)
        val titlTextView1 = holder.itemView.findViewById<TextView>(R.id.tv_money_tittle)
        val titlTextView2 = holder.itemView.findViewById<TextView>(R.id.tv_calendar_tittle)
        val titlTextView3 = holder.itemView.findViewById<TextView>(R.id.tv_nohome_tittle)
        val moreBtn=holder.itemView.findViewById<Button>(R.id.detail_button)
        titlTextView1.text = roboList.get(position).msgFirst
        titlTextView2.text = roboList.get(position).msgSecond
        titlTextView3.text = roboList.get(position).msgThird
        moreBtn.setOnClickListener {
            Log.d("click======>",roboList.get(position).name)
        }

    }

    override fun getItemCount(): Int {
        return roboList.size
    }

}