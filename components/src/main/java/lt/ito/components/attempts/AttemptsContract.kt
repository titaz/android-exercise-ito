package lt.ito.components.attempts

import lt.ito.components.base.BaseScreenContract
import lt.ito.models.Dish

interface AttemptsContract : BaseScreenContract {
    fun displayDishes(it: List<Dish>)
    fun displayNoDishes()
}
