package lt.ito

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.TestScheduler
import lt.ito.components.schedulers.SchedulerProvider

class MockSchedulerProvider : SchedulerProvider {
    val testScheduler = TestScheduler()
    override fun ui(): Scheduler = testScheduler

    override fun computation(): Scheduler = testScheduler

    override fun io(): Scheduler = testScheduler

    override fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(testScheduler).observeOn(testScheduler)
        }
    }

    override fun applyCompletableSchedulers(): CompletableTransformer {
        return CompletableTransformer {
            it.subscribeOn(testScheduler).observeOn(testScheduler)
        }
    }

    override fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { it.subscribeOn(testScheduler).observeOn(testScheduler) }
    }
}