package xyz.kfdykme.cardtravels.page.travel.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_card_detail.view.*
import xyz.kfdykme.cardtravels.R

/**
 * Created by kf on 18-1-21.
 */
class CardDetaiAdapter(val context: Context,var items : ArrayList<String>,val type :Int  ) : RecyclerView.Adapter<CardDetaiAdapter.CardDetaiViewHolder>() {
    override fun getItemCount(): Int {
        return items.size;
    }

    override fun onBindViewHolder(holder: CardDetaiViewHolder?, position: Int) {
      holder?.bind(items.get(position),position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardDetaiViewHolder {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.view_card_detail,parent,false)
        return CardDetaiViewHolder(view)
    }

    class CardDetaiViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var mainColor = Color.parseColor("#333333")

        fun bind(title:String,position : Int) {
            itemView.cardTitle.setText(title)
            itemView.cardTitle.setTextColor(mainColor)
            itemView.vTitleLine.setBackgroundColor(mainColor)
            itemView.cardv.setBackgroundColor(mainColor)
        }

        init {
            mainColor =Color.rgb(getRandom(),getRandom(),getRandom())
        }

        fun getRandom():Int{
            return (100+ (Math.random() *150)).toInt()
        }

    }
}