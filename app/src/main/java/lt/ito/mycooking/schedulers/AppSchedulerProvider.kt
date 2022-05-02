package lt.ito.mycooking.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lt.ito.components.schedulers.SchedulerProvider

class AppSchedulerProvider : SchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

}