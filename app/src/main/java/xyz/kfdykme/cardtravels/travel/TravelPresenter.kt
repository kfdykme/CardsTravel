package xyz.kfdykme.cardtravels.travel

/**
 * Created by wimkf on 2018/3/3.
 */


class TravelPresenter:TravelContract.Presenter{
    private lateinit var mTravelView:TravelContract.View

    constructor(travelView: TravelView){
        mTravelView = travelView

        mTravelView.setPresenter(this)
    }


    override fun start() {


    }

}