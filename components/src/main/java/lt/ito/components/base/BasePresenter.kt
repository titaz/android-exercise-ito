package lt.ito.components.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T> {
    protected val subscriptions = CompositeDisposable()
    var view: T? = null
        set(value) {
            field = value
            if (value == null) {
                subscriptions.clear()
            }
        }
}
