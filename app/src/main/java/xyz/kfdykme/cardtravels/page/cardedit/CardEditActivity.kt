package xyz.kfdykme.cardtravels.page.cardedit

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import xyz.kfdykme.cardtravels.R

import kotlinx.android.synthetic.main.activity_card_edit.*
import kotlinx.android.synthetic.main.content_card_edit.*
import kotlinx.android.synthetic.main.view_card_detail.*
import android.view.Gravity
import kotlinx.android.synthetic.main.view_tool_basic_detail.view.*
import java.text.FieldPosition


class CardEditActivity : AppCompatActivity() {

    val NORMAL_TOUCH_STATUS = 1
    val ALERT_TOUCH_STATUS = 2
    var touchStatus:Int = NORMAL_TOUCH_STATUS


    var wmlp:WindowManager.LayoutParams? = null
    var wm:WindowManager? = null//application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    var inflater:LayoutInflater? = null//LayoutInflater.from(this@CardEditActivity)
    var v:View? = null//inflater.inflate(R.layout.view_tool_basic_detail,null)

    var baseX:Int? = null
    var baseY:Int? = null

    var touchListener:View.OnTouchListener? = null

    var details:ArrayList<String> = ArrayList<String>(listOf("Target"))
    var detailAdapter = ToolAdapter(this,details,1)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_edit)
        setSupportActionBar(toolbar)

        wmlp = WindowManager.LayoutParams()
        wm = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        inflater = LayoutInflater.from(this@CardEditActivity)


        
        var tools:ArrayList<String> = ArrayList<String>(listOf("Target","Eat","Play"))
        var adapter = ToolAdapter(this,tools,0)


        rvTools.adapter = adapter
        rvTools.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object:ToolAdapter.OnItemClickListener{

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
                    "Play"->v = inflater?.inflate(R.layout.view_tool_play_detail,null)
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
                v?.et?.setOnTouchListener(touchListener)
                wm?.addView(v,wmlp)

                v?.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
                        ,View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED))
                touchStatus = ALERT_TOUCH_STATUS
                return false

            }
        })

        rvCardDetail.adapter = detailAdapter
        rvCardDetail.layoutManager = LinearLayoutManager(this)

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when(touchStatus){
            NORMAL_TOUCH_STATUS->return super.dispatchTouchEvent(ev)
            ALERT_TOUCH_STATUS->{
                touchListener?.onTouch(v,ev)
                return false
            }
        }
        return false
    }
}
