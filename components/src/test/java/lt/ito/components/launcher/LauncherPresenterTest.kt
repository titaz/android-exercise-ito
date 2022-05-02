package lt.ito.components.launcher

import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import lt.ito.MockSchedulerProvider
import lt.ito.components.test.IdlingResourceCountable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class LauncherPresenterTest {

    @Mock
    lateinit var launcherContract: LauncherContract

    @Mock
    lateinit var idlingResourceCounter: IdlingResourceCountable

    var appScheduler = MockSchedulerProvider()

    private lateinit var launcherPresenter: LauncherPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        launcherPresenter = LauncherPresenter(appScheduler, idlingResourceCounter)
        launcherPresenter.view = launcherContract
    }

    @Test
    fun onAttach() {
        launcherPresenter.onAttach()
        verify(launcherContract, never()).openMainScreen()

        appScheduler.testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        verify(launcherContract).openMainScreen()
    }
}