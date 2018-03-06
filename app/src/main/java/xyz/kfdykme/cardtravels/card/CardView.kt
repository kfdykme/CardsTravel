package xyz.kfdykme.cardtravels.card

import android.content.Context
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.view_card_detail.*

import xyz.kfdykme.cardtravels.R
import xyz.kfdykme.cardtravels.data.Card


/**
 * Created by wimkf on 2018/3/3.
 */
class CardView:Fragment, CardContract.View{

    private lateinit var mPresenter:CardContract.Presenter

    var card: Card? = null

    val NORMAL_TOUCH_STATUS = 1
    val ALERT_TOUCH_STATUS = 2
    var touchStatus:Int = NORMAL_TOUCH_STATUS

    var wmlp: WindowManager.LayoutParams? = null
    var wm: WindowManager? = null//application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    var inflater:LayoutInflater? = null//LayoutInflater.from(this@CardEditActivity)
    var v:View? = null//inflater.inflate(R.layout.view_tool_basic_detail,null)

    var baseX:Int? = null
    var baseY:Int? = null

    var touchListener:View.OnTouchListener? = null

    lateinit var details:ArrayList<String>
    lateinit var detailAdapter :ToolAdapter

    var tool:String? = null

    fun initTouch(){
        touchListener = object:View.OnTouchListener{
            var lX:Int = baseX!!
            var lY:Int = baseY!!

            override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {

                val rx = motionEvent?.rawX?.toInt()!!
                val ry = motionEvent?.rawY?.toInt()!!

                when(motionEvent?.action){
                    MotionEvent.ACTION_UP ->{
                        wm?.removeViewImmediate(v)
                        touchStatus = NORMAL_TOUCH_STATUS


                        val x1 = cardv.left+cardv.x
                        val x2 = cardv.left+cardv.x + cardv.width
                        val y1 = cardv.top+cardv.y
                        val y2 = cardv.top+cardv.y + cardv.height
                        var x = motionEvent?.rawX!!
                        var y= motionEvent?.rawY!!
                        if(x>x1 && x <x2 && y>y1 && y < y2){
                            details.add(tool!!)
                            detailAdapter.notifyDataSetChanged()
                        }
                    }

                    MotionEvent.ACTION_MOVE ->{
                        wmlp!!.x +=(rx-lX)

                        wmlp!!.y +=(ry-lY)

                        try {
                            wm!!.updateViewLayout(v,wmlp)
                        } catch (e:IllegalArgumentException){

                        }

                    }
                }

                lX = rx
                lY = ry

                return false
            }
        }
    }

    constructor(){

    }

    fun doSave() {

        detailAdapter.doSave()
    }


    fun load(card:Card?){
        if(card==null) return
        Log.i("CardView",card.content)

        detailAdapter.doLoad(card!!)
    }

    override fun onCreateView(inflate: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root:View = inflate?.inflate(R.layout.activity_card_edit,container,false)!!


        details = ArrayList<String>()
        detailAdapter = ToolAdapter(context!!, details, 1)

        wmlp = WindowManager.LayoutParams()
        wm = context!!.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        inflater = LayoutInflater.from(context)


        var tools:ArrayList<String> = ArrayList<String>(listOf("Target","Eat"))
        var adapter = ToolAdapter(context!!, tools, 0)


        var rvTools = root.findViewById<RecyclerView>(R.id.rvTools)
        rvTools.adapter = adapter
        rvTools.layoutManager = LinearLayoutManager(context)

        adapter.setOnItemClickListener(object: ToolAdapter.OnItemClickListener {

            override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                when(motionEvent?.action){
                    MotionEvent.ACTION_DOWN->{
                        baseX = motionEvent.rawX.toInt()
                        baseY = motionEvent.rawY.toInt()


                    }
                }
                return false
            }

            override fun onClick(view: View?) {

            }

            override fun onLongClick(view: View?,position: Int?): Boolean {
                tool = adapter.items[position!!]

                try{
                    wm?.removeViewImmediate(v)

                } catch(e:Exception){

                }
                initTouch()
                when(tool){
                    "Target"-> v = inflater?.inflate(R.layout.view_tool_basic_detail,null)
                    "Eat"-> v = inflater?.inflate(R.layout.view_tool_eat_detail,null)
                    else ->v = inflater?.inflate(R.layout.view_tool_basic_detail,null)
                }
                val w:Int = (view!!.width * 2).toInt()
                val h:Int = (view!!.height * 2).toInt()

                wmlp?.type = WindowManager.LayoutParams.TYPE_PHONE

                wmlp?.format = PixelFormat.RGBA_8888
                wmlp?.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                wmlp?.width = w
                wmlp?.height = h
                wmlp?.gravity = Gravity.LEFT or Gravity.TOP
                wmlp?.x = baseX!!-w/2
                wmlp?.y = baseY!!-h/2

                v?.setOnTouchListener(touchListener)
                wm?.addView(v,wmlp)

                v?.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
                        ,View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED))
                touchStatus = ALERT_TOUCH_STATUS
                return false

            }
        })

        var rvCardDetail = root.findViewById<RecyclerView>(R.id.rvCardDetail)
        rvCardDetail.adapter = detailAdapter
        rvCardDetail.layoutManager = LinearLayoutManager(context)


        load(card)
        return root
    }

    override fun setPresenter(presenter: CardContract.Presenter) {
        mPresenter = presenter
    }

    override fun onResume() {
        super.onResume()
//        mPresenter.start()
    }


    companion object {
        fun newInstance():CardView{
            return CardView()
        }
    }
}