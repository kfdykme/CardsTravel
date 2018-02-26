package xyz.kfdykme.cardtravels.card.holder

import android.view.View
import kotlinx.android.synthetic.main.view_tool_basic_detail.view.*
import xyz.kfdykme.cardtravels.card.ToolAdapter
import xyz.kfdykme.cardtravels.data.TargetCardItem

/**
 * Created by wimkf on 2018/2/22.
 */
public class TargetHolder(view:View) : ToolAdapter.ToolBaseViewHolder(view){

    var item:TargetCardItem = TargetCardItem()

    init {

        view.et.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(p0: View?, p1: Boolean) {
                item.target = view.et.text.toString()

            }
        })
    }
}