package lt.ito.components.test

import androidx.test.espresso.IdlingResource

interface IdlingResourceCountable {
    val idlingResource: IdlingResource
    fun increase()
    fun decrease()
}
