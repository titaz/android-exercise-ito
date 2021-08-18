package lt.ito.components.respositories

import io.reactivex.Completable
import io.reactivex.Single
import lt.ito.models.Dish
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DishesRepository @Inject constructor() {

    private val dishes = mutableListOf<Dish>()

    fun getDishes(): Single<List<Dish>> {
        return Single
            .just(dishes.toList())
            .delay(2, TimeUnit.SECONDS)
    }

    fun add(dish: Dish): Completable {
        dishes.add(dish)
        return Completable.complete()
    }
}