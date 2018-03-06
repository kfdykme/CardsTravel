package xyz.kfdykme.cardtravels.data

/**
 * Created by wimkf on 2018/3/5.
 */
class CardItem{


    var target:String = ""

    var toolList:MutableList<String> = mutableListOf()


    var eats:MutableList<EatItem> = mutableListOf()

    class EatItem{
        var content:String = ""
        constructor(c:String){
            content = c
        }
    }
}