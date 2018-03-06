package xyz.kfdykme.cardtravels.data

/**
 * Created by wimkf on 2018/2/22.
 */

public class EatCardItem : BaseCardItem(){

    object name{
        var NAME = "EatCardItem"
    }

    var list:MutableList<Item> = mutableListOf()

    init {
        itemName = "EatCardItem"

    }

    public class Item(c:String){

        var content:String = ""
        init {
            content = c
        }
    }
}