package xyz.kfdykme.cardtravels.card.holder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.view_eat_edittext.view.*
import kotlinx.android.synthetic.main.view_tool_eat_detail.view.*
import xyz.kfdykme.cardtravels.card.ToolAdapter
import xyz.kfdykme.cardtravels.cview.EatEditText
import xyz.kfdykme.cardtravels.data.EatCardItem

/**
 * Created by wimkf on 2018/2/24.
 */
public class EatHolder(view:View):ToolAdapter.ToolBaseViewHolder(view){

    companion object {
        val TAG = "Eat"
    }

    var item:EatCardItem = EatCardItem()

    init {
        view.btAdd.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                addItem("")
            }
        })
    }

    fun addItem(s:String){
        var eet = EatEditText(itemView.context)
        item.list.add(EatCardItem.Item(s))
        eet.et.setText(s)
        eet.et.addTextChangedListener(object:TextWatcher{
            val pos = item.list.size-1

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                item.list.get(pos)?.content = s.toString()
            }
        })
        itemView.ll.addView(eet)
    }
}