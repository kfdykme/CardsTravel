package xyz.kfdykme.cardtravels.data


import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by wimkf on 2018/2/22.
 */
open class Card:RealmObject(){
    var cardId:String = ""
    var content:String = ""
    var cardName:String =""

}