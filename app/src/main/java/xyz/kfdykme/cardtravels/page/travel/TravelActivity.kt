package xyz.kfdykme.cardtravels.page.travel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import xyz.kfdykme.cardtravels.R

import kotlinx.android.synthetic.main.activity_travel.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_travel.*
import xyz.kfdykme.cardtravels.page.cards.adapter.CardsAdapter
import xyz.kfdykme.cardtravels.page.travel.adapter.CardDetaiAdapter

class TravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)
        setSupportActionBar(toolbar)
        var cItems:java.util.ArrayList<String> = java.util.ArrayList<String>(listOf("ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","ChengDu","BeiJing","GuangZhou"))

        var tItems = ArrayList<String>()
        tItems.add("Item Lists")

        var adapter = CardsAdapter(this,cItems,1)

        var tAdapter = CardDetaiAdapter(this,tItems,0)

        rvCards.adapter =adapter
        rvCards.layoutManager = LinearLayoutManager(this)


        rvTravle.adapter = tAdapter
        rvTravle.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object :CardsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val s :String =adapter.items.get(position)
                tItems.add(tAdapter.items.size,s)

                tAdapter.notifyItemInserted(tAdapter.items.size)

                rvTravle.smoothScrollToPosition(tAdapter.items.size)
            }
        })
    }

}
