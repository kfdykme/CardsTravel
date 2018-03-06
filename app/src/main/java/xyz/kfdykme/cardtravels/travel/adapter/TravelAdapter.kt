package xyz.kfdykme.cardtravels.travel.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_card_detail.view.*
import xyz.kfdykme.cardtravels.R
import xyz.kfdykme.cardtravels.card.ToolAdapter
import xyz.kfdykme.cardtravels.data.Card

/**
 * Created by wimkf on 2018/3/4.
 */
class  TravelAdapter : RecyclerView.Adapter<TravelAdapter.CardShowViewHolder>  {

    var context :Context? = null

    var list = mutableListOf<Card>()

    constructor(context: Context?,list:MutableList<Card>){
        this.list = list
        this.context = context
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardShowViewHolder {
        return CardShowViewHolder(LayoutInflater.from(context).inflate(
                R.layout.view_card_detail,parent,false
        ))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CardShowViewHolder?, position: Int) {

        holder?.bind(list[position],position)
    }


    class CardShowViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


        var adapter:ToolAdapter? = null

        var hasLoaded = false
        fun bind(card:Card,pos:Int){
            itemView.cardTitle.setText(card.cardName)
            if(!hasLoaded) {
                hasLoaded = true
                adapter!!.doLoad(card)
            }

            itemView.cardTitle.setTextColor(card.primaryColor)
            itemView.vTitleLine.setBackgroundColor(card.primaryColor)
            itemView.cardv.setCardBackgroundColor(card.primaryColor)

        }

        init {
            var tools:ArrayList<String> = ArrayList<String>()
            adapter = ToolAdapter(itemView!!.context, tools, 1)
            itemView.rvCardDetail.layoutManager = LinearLayoutManager(itemView!!.context)
            itemView.rvCardDetail.adapter = adapter
        }
    }

}