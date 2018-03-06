package xyz.kfdykme.cardtravels

/**
 * Created by wimkf on 2018/3/3.
 */
interface BaseView<T:BasePresenter>{
    fun setPresenter(presenter:T)
}