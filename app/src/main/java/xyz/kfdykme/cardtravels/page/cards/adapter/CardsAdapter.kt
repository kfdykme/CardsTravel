package xyz.kfdykme.cardtravels.page.cards.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import kotlinx.android.synthetic.main.view_card.view.*
import xyz.kfdykme.cardtravels.R

/**
 * Created by kf on 18-1-21.
 */
class CardsAdapter(val context : Context, val items : ArrayList<String>,val type :Int) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>(){



    //rewrite onclicklistener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    var mOnClickListener:OnItemClickListener? = null

    fun setOnItemClickListener(l:OnItemClickListener){
        mOnClickListener = l
    }

    override fun onBindViewHolder(holder: CardsViewHolder?, position: Int) {


       holder?.bind(items.get(position),type)

       holder?.itemView?.cardv?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                if(mOnClickListener!=null){
                    mOnClickListener!!.onItemClick(position)
                }

            }
        })


    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardsViewHolder {
        return CardsViewHolder(LayoutInflater.from(context).inflate(R.layout.view_card,parent,false))
    }


    class CardsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var cardMainColor = Color.parseColor("#333333")

        fun bind(title:String,type:Int){
            itemView.cardv.setBackgroundColor(cardMainColor)

            itemView.cardTitle.setText(title)
            if(type == 1)
            itemView.cardTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,15f);
            itemView.cardLine1.setBackgroundColor(cardMainColor)
            itemView.cardLine2.setBackgroundColor(cardMainColor)
            itemView.cardLine3.setBackgroundColor(cardMainColor)


        }

        init {
                cardMainColor =Color.rgb(getRandom(),getRandom(),getRandom())

        }

        fun getRandom():Int{
            return (100+ (Math.random() *150)).toInt()
        }


    }
}
