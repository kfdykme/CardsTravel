package xyz.kfdykme.cardtravels

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.*

import kotlinx.android.synthetic.main.content_main.*
import xyz.kfdykme.cardtravels.card.CardPresenter
import xyz.kfdykme.cardtravels.card.CardView
import xyz.kfdykme.cardtravels.data.Card
import xyz.kfdykme.cardtravels.travel.TravelPresenter
import xyz.kfdykme.cardtravels.travel.TravelView
import xyz.kfdykme.cardtravels.util.ActivityUtils

class MainActivity : AppCompatActivity() ,OnFragmentChangeListener{


    val STATE_TRAVEL = 0

    val STATE_CARD= 1

    var context: Context? = null

    var cardFragment:CardView? = null

    var travelFragment : TravelView? = null

    var state:Int = STATE_TRAVEL

    override fun onAdd(type: String) {
        when(type){
            "card"->{
                ActivityUtils.addFragmentToActivity(supportFragmentManager, cardFragment!!,R.id.travelFrame)

            }
            "travel"->{
                ActivityUtils.addFragmentToActivity(supportFragmentManager, travelFragment!!,R.id.travelFrame)

            }
        }

    }

    override fun onReplace(type: String) {

        when(type){
            "card"->{
                ActivityUtils.replaceFragmentToActivity(supportFragmentManager,cardFragment!!,R.id.travelFrame)
                state = STATE_CARD

            }
            "travel"->{
                ActivityUtils.replaceFragmentToActivity(supportFragmentManager,travelFragment!!,R.id.travelFrame)
                state = STATE_TRAVEL

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this


        travelFragment = supportFragmentManager.findFragmentById(R.id.travelFrame) as TravelView?

        if(travelFragment == null){
            travelFragment = TravelView.newInstance()
            onAdd("travel")
            travelFragment?.setOnOpenCardListener(object :TravelView.OnOpenCardListener{
                override fun open(card: Card) {

                        cardFragment = CardView.newInstance()
                        cardFragment!!.card = card

                        btAdd.visibility = View.GONE
                        onReplace("card")
                        var cardPresenter: CardPresenter = CardPresenter(cardFragment!!)

                }

            })
        }


        var travelPresenter = TravelPresenter(travelFragment!!)


        btAdd.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                cardFragment = CardView.newInstance()
                onReplace("card")
                var cardPresenter: CardPresenter = CardPresenter(cardFragment!!)
                btAdd.visibility = View.GONE
            }
        })
    }

    override fun onBackPressed() {
        when(state){
            STATE_CARD->{
                AlertDialog.Builder(this)
                        .setMessage(getString(R.string.askSaveCardEdit))
                        .setNegativeButton("No",object: DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                onReplace("travel")
                                btAdd.visibility = View.VISIBLE
                            }
                        })
                        .setPositiveButton("Yes",object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {

                                cardFragment?.doSave()
                                onReplace("travel")
                                btAdd.visibility = View.VISIBLE
                            }
                        })
                        .create().show()
            }
        }



    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when(cardFragment?.touchStatus){
            cardFragment?.NORMAL_TOUCH_STATUS->return super.dispatchTouchEvent(ev)
            cardFragment?.ALERT_TOUCH_STATUS->{
                cardFragment?.touchListener?.onTouch(cardFragment?.v,ev)
                return false
            }
        }
        return false
    }



}
