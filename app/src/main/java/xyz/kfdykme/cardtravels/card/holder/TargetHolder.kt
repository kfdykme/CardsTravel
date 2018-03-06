package xyz.kfdykme.cardtravels.card.holder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.view_tool_basic_detail.view.*
import xyz.kfdykme.cardtravels.card.ToolAdapter
import xyz.kfdykme.cardtravels.data.TargetCardItem

/**
 * Created by wimkf on 2018/2/22.
 */
public class TargetHolder(view:View) : ToolAdapter.ToolBaseViewHolder(view){
    companion object {
        val TAG = "Target"
    }

    var item:TargetCardItem = TargetCardItem()

    init {

        view.et.addTextChangedListener(object: TextWatcher {


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                item.target = s.toString()
            }
        })
    }
}