package xyz.kfdykme.cardtravels.cview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_eat_edittext.view.*
import xyz.kfdykme.cardtravels.R

/**
 * Created by wimkf on 2018/2/24.
 */

public class EatEditText(context: Context?) : RelativeLayout(context) {


    init {
        LayoutInflater.from(context)?.inflate(R.layout.view_eat_edittext,this)

    }

}