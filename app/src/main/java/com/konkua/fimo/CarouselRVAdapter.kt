package com.konkua.fimo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarouselRVAdapter(private val roboList: ArrayList<Robocash>, val mContext:Context) :
    RecyclerView.Adapter<CarouselRVAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.textview)
        val imageView = holder.itemView.findViewById<ImageView>(R.id.view_img)
        val titlTextView1 = holder.itemView.findViewById<TextView>(R.id.tv_money_tittle)
        val titlTextView2 = holder.itemView.findViewById<TextView>(R.id.tv_calendar_tittle)
        val titlTextView3 = holder.itemView.findViewById<TextView>(R.id.tv_nohome_tittle)
        val msgTextView1 = holder.itemView.findViewById<TextView>(R.id.tv_money_des)
        val msgTextView2 = holder.itemView.findViewById<TextView>(R.id.tv_calendar_des)
        val msgTextView3 = holder.itemView.findViewById<TextView>(R.id.tv_nohome_des)
        val moreBtn = holder.itemView.findViewById<Button>(R.id.detail_button)
        titlTextView1.text = roboList.get(position).titleFirst
        titlTextView2.text = roboList.get(position).titleSecond
        titlTextView3.text = roboList.get(position).titleThird
        msgTextView1.text = roboList.get(position).msgFirst
        msgTextView2.text = roboList.get(position).msgSecond
        msgTextView3.text = roboList.get(position).msgThird
        Glide
            .with(mContext)
            .load(roboList.get(position).iconLink)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView);
        moreBtn.setOnClickListener {
            val intent = Intent(mContext, WebViewActivity::class.java)
            intent.putExtra("LINK", roboList.get(position).affiliate)
            mContext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return roboList.size
    }
    fun update(items: List<Robocash>) {
        roboList.clear()
        roboList.addAll(items)
        notifyDataSetChanged()

    }
}