package xyz.kfdykme.cardtravels.page.cards

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import xyz.kfdykme.cardtravels.R
import xyz.kfdykme.cardtravels.page.cardedit.CardEditActivity
import xyz.kfdykme.cardtravels.page.cards.adapter.CardsAdapter
import xyz.kfdykme.cardtravels.page.travel.TravelActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //test data
        val items = ArrayList<String>(listOf("ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","GuangZhou") )

        //init recyclerview
        rv.adapter = CardsAdapter(this,items,0)
        rv.layoutManager = GridLayoutManager(this,2)


        //init bottom navigation(contains two buttons)
        btTravel.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View?) {
                val intent = Intent()
                intent.setClass(this@MainActivity,TravelActivity::class.java)
                startActivity(intent)
            }
        })
        btCard.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {

                startActivity(Intent(this@MainActivity,CardEditActivity::class.java))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
