package xyz.kfdykme.cardtravels.travel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import xyz.kfdykme.cardtravels.R

import kotlinx.android.synthetic.main.content_travel.*
import xyz.kfdykme.cardtravels.data.Card
import xyz.kfdykme.cardtravels.page.cards.adapter.CardsAdapter
import xyz.kfdykme.cardtravels.page.travel.adapter.CardDetaiAdapter

class TravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)
        var cItems = mutableListOf<Card>()
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
                val s :String =adapter.items.get(position).cardId

                tItems.add(tAdapter.items.size,s)

                tAdapter.notifyItemInserted(tAdapter.items.size)

                rvTravle.smoothScrollToPosition(tAdapter.items.size)
            }
        })
    }

}
