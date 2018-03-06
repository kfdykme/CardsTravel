package xyz.kfdykme.cardtravels.card

/**
 * Created by wimkf on 2018/3/4.
 */
class CardPresenter :CardContract.Presenter{

    private lateinit var view:CardView

    constructor(view:CardView){
        this.view = view
        view.setPresenter(this)
    }

    override fun start() {

    }

}