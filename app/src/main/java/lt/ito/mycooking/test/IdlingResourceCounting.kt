package lt.ito.mycooking.test

import androidx.test.espresso.idling.CountingIdlingResource
import lt.ito.components.test.IdlingResourceCountable

class IdlingResourceCounting : IdlingResourceCountable {

    override val idlingResource = CountingIdlingResource("IdentiwayIdlingResource")

    override fun increase() {
        idlingResource.increment()
    }

    override fun decrease() {
        idlingResource.decrement()
    }
}
