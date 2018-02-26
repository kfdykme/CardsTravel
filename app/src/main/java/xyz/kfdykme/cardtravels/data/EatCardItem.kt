package xyz.kfdykme.cardtravels.data

/**
 * Created by wimkf on 2018/2/22.
 */

public class EatCardItem : BaseCardItem(){



    var list:MutableList<Item> = mutableListOf()

    init {
        itemName = "EatCardItem"

    }

    public class Item(val c:String){

        var content:String = ""
        init {
            content = c
        }
    }
}