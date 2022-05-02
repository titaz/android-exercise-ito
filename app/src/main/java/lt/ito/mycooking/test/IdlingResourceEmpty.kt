package lt.ito.mycooking.test

import androidx.test.espresso.IdlingResource
import lt.ito.components.test.IdlingResourceCountable

class IdlingResourceEmpty : IdlingResourceCountable {

    override var idlingResource: IdlingResource = object : IdlingResource {
        override fun getName(): String {
            return "EmptyResource"
        }

        override fun isIdleNow(): Boolean {
            return true
        }

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {}
    }

    override fun increase() {}

    override fun decrease() {}

}
