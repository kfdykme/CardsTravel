package xyz.kfdykme.cardtravels.page.cardedit

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by kf on 18-1-21.
 */
class ToolAdapter(val context :Context,var items:List<String>,val type :Int) : RecyclerView.Adapter<ToolAdapter.ToolViewHolder>() {
    override fun onBindViewHolder(holder: ToolViewHolder?, position: Int) {



    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ToolViewHolder {
        val inflater :LayoutInflater = LayoutInflater.from(context);
        val view:View? = null
        when(viewType){}

    }

    class ToolViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}