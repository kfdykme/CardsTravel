package xyz.kfdykme.cardtravels.page.cardedit

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import xyz.kfdykme.cardtravels.R

import kotlinx.android.synthetic.main.activity_card_edit.*

class CardEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_edit)
        setSupportActionBar(toolbar)
        var tools:ArrayList<String> = ArrayList<String>(listOf("Eat","Look","Play"))


    }

}
