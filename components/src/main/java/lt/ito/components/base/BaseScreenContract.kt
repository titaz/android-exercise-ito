package lt.ito.components.base


interface BaseScreenContract {
    fun showError(it: Throwable)
    fun showProgress()
    fun hideProgress()
}
