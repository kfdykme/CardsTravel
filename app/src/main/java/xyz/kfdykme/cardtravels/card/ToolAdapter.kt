package xyz.kfdykme.cardtravels.card

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import io.realm.Realm
import xyz.kfdykme.cardtravels.R

import kotlinx.android.synthetic.main.view_tool_basic.view.*
import xyz.kfdykme.cardtravels.card.holder.EatHolder
import xyz.kfdykme.cardtravels.card.holder.TargetHolder
import xyz.kfdykme.cardtravels.data.BaseCardItem
import xyz.kfdykme.cardtravels.data.Card

/**
 * Created by kf on 18-1-21.
 */
class ToolAdapter(val context :Context,var items:List<String>,val type :Int)
    : RecyclerView.Adapter<ToolAdapter.ToolBaseViewHolder?>() {

    val TOOL_PLACE:Int = 0

    val TOOL_EAT:Int = 1

    val TOOL_LOOK:Int = 2

    val TOOL_PLAY:Int = 3

    var holders:MutableList<ToolBaseViewHolder> = mutableListOf()

    interface OnItemClickListener{
        fun onClick(view: View?)
        fun onTouch(view:View?, motionEvent: MotionEvent?):Boolean
        fun onLongClick(view:View?,position: Int?):Boolean
    }

    var mOnClickListener : OnItemClickListener? =null

    fun setOnItemClickListener(l: OnItemClickListener){
        mOnClickListener =l
    }

    override fun onBindViewHolder(holder: ToolBaseViewHolder?, position: Int) {
        when(type){
            0 ->  {
                (holder as ToolViewHolder)?.bind(items.get(position))
                holder?.itemView.setOnClickListener(object :View.OnClickListener{
                    override fun onClick(view: View?) {
                            mOnClickListener!!.onClick(view)
                    }
                })
                holder?.itemView.setOnTouchListener(object :View.OnTouchListener{
                    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                        return mOnClickListener!!.onTouch(view,motionEvent)
                    }
                })
                holder?.itemView.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(view: View?): Boolean {
                        return mOnClickListener!!.onLongClick(view,position)
                    }

                })
            }



            1 ->{
                when(getItemViewType(position)){
                    0 -> {
                        (holder as TargetHolder).itemView.setOnClickListener(object :View.OnClickListener{
                            override fun onClick(p0: View?) {

                                Toast.makeText(context,(holder as TargetHolder).item.target,Toast.LENGTH_LONG).show()
                            }
                        })
                    }
                    1 ->{

                    }
                    else -> (holder as ToolBasicDetailViewHolder)?.bind(items.get(position))
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ToolBaseViewHolder? {




        val inflater :LayoutInflater = LayoutInflater.from(context);
        var view:View? = null


        if(type==0)
        when(viewType){

            0,1,2,3-> {
                view = inflater.inflate(R.layout.view_tool_basic,parent,false)
                return ToolViewHolder(view)
            }
            else -> return null
        }
        else if (type ==1)
            when(viewType){
                0 ->{
                    view = inflater.inflate(R.layout.view_tool_basic_detail,parent,false)
                    var holder =TargetHolder(view)
                    holders.add(holder)
                    return holder
            }
                1 ->{
                    view = inflater.inflate(R.layout.view_tool_eat_detail,parent,false)
                    var holder =EatHolder(view)
                    holders.add(holder)
                    return holder
                }
                else -> return null
            }
        else return null
    }

    override fun getItemViewType(position: Int): Int {
        when(items.get(position)){
            "Target"-> return 0
            "Eat"-> return 1
            "Play"->return 2
            else ->return 4
        }

    }


    open class ToolBaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){

    }

    class ToolViewHolder(itemView: View?) : ToolBaseViewHolder(itemView) {

        fun bind(title:String){
            itemView?.tv?.text = title

        }

        init {
            itemView?.tv?.text = "Basic"
        }
    }

    class ToolBasicDetailViewHolder(itemView: View?) : ToolBaseViewHolder(itemView){

        fun bind(title:String){

        }

        init{

        }
    }

    fun doSave() {
        var items:MutableList<BaseCardItem> = mutableListOf()
        var name:String =""
        for(holder in holders){

            if(holder is EatHolder){
                var hd :EatHolder = holder as EatHolder
                items.add(hd.item)
            }

            if(holder is TargetHolder){
                var hd :TargetHolder = holder as TargetHolder
                items.add(hd.item)
                name = hd.item.target
            }
        }

        var card:Card
        Realm.getDefaultInstance().executeTransaction(Realm.Transaction {
            card = it.createObject(Card::class.java)
            card.cardName = name
            card.content = Gson().toJson(items)

        })
    }
}