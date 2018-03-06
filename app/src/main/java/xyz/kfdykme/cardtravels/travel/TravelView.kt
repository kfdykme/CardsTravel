package xyz.kfdykme.cardtravels.travel

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import kotlinx.android.synthetic.main.travel_frag.*
import xyz.kfdykme.cardtravels.R
import xyz.kfdykme.cardtravels.data.Card
import xyz.kfdykme.cardtravels.page.cards.adapter.CardsAdapter
import xyz.kfdykme.cardtravels.travel.adapter.TravelAdapter
import xyz.kfdykme.view.HoriGroup


/**
 * Created by wimkf on 2018/3/3.
 */
class TravelView : Fragment,TravelContract.View{

    private lateinit var mPresenter:TravelContract.Presenter

    private lateinit var cardDetailAdapter:TravelAdapter

    private lateinit var root:View

    val TAG:String = "TravelView"

    interface OnOpenCardListener{
        fun open(card:Card)
    }

    var mOnOpenCardListener:OnOpenCardListener? = null

    fun setOnOpenCardListener(l:OnOpenCardListener){
        mOnOpenCardListener = l
    }

    constructor(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root:View = inflater?.inflate(R.layout.travel_frag,container,false)!!
        var tItems = mutableListOf<Card>()

        cardDetailAdapter = TravelAdapter(context,tItems!!)

        var trv = root.findViewById<RecyclerView>(R.id.trv)
        trv.layoutManager = LinearLayoutManager(context)




        trv.adapter = cardDetailAdapter

        var items = Realm.getDefaultInstance().where(Card::class.java).findAll()
        Log.i(TAG,"${items.size}")
        //init recyclerview

        var crv = root.findViewById<RecyclerView>(R.id.crv)
        crv.layoutManager = GridLayoutManager(context,2)

        var adapter =  CardsAdapter(context, items!!,0)
        crv.adapter = adapter
        crv.addOnItemTouchListener(object:RecyclerView.OnItemTouchListener{
            override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
                horigroup.onTouchEvent(e)
            }

            override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
                horigroup.onTouchEvent(e)
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })

        adapter.setOnItemClickListener(object :CardsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                when(horigroup.state){
                    HoriGroup.STATE_COMBINE->{
                        var card = adapter.items.get(position)
                        tItems.add(card)
                        cardDetailAdapter.notifyDataSetChanged()
                        //horigroup.reflashViews()
                        Log.i(TAG,"onItemCLick ${card.cardName}")
                    }
                    HoriGroup.STATE_LEFT->{
                        mOnOpenCardListener?.open(items.get(position)!!)
                    }
                }

            }
        })

        val horiGroup = root.findViewById<HoriGroup>(R.id.horigroup)
        horiGroup.setAnimationListener(object :HoriGroup.AnimationListener{

            override fun onStart(state: Int) {
                Log.i(TAG,"onStart")
                when(state){
                    HoriGroup.STATE_COMBINE->{
                        crv.layoutManager = LinearLayoutManager(context)
                    }
                }
            }
            override fun onSuccess(state: Int) {
                Log.i(TAG,"onSuccess")
                when(state){
                    HoriGroup.STATE_LEFT->{
                        crv.layoutManager = GridLayoutManager(context,2)
                    }
                }
            }
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        mPresenter.start()
    }

    override fun setPresenter(presenter: TravelContract.Presenter) {
        mPresenter = presenter
    }

    companion object {

        fun newInstance():TravelView{
            return TravelView()
        }

    }



}