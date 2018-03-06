package xyz.kfdykme.cardtravels.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by wimkf on 2018/3/5.
 */
open class Card:RealmObject(){
    @PrimaryKey
    var cardId:Long = 0
    
    var cardName:String = ""
    
    var content:String = ""

    var primaryColor:Int = 0
}