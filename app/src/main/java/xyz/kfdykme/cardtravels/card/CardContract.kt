package xyz.kfdykme.cardtravels.card

import xyz.kfdykme.cardtravels.BasePresenter
import xyz.kfdykme.cardtravels.BaseView

/**
 * Created by wimkf on 2018/3/3.
 */
interface CardContract{

    interface Presenter:BasePresenter{

    }

    interface View:BaseView<Presenter>{

    }
}