package lt.ito.mycooking.launcher

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import it.ito.mycooking.R
import lt.ito.components.launcher.LauncherContract
import lt.ito.components.launcher.LauncherPresenter
import lt.ito.mycooking.ITOApplication
import lt.ito.mycooking.MainActivity
import lt.ito.mycooking.utils.stringResId
import javax.inject.Inject

class LauncherActivity : AppCompatActivity(), LauncherContract {

    @Inject
    lateinit var launcherPresenter: LauncherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ITOApplication.appComponent(this).inject(this)
        setContentView(R.layout.activity_launcher)
    }

    override fun onStart() {
        super.onStart()
        launcherPresenter.view = this
        launcherPresenter.onAttach()
    }

    override fun onStop() {
        super.onStop()
        launcherPresenter.view = null
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showError(it: Throwable) {
        Toast.makeText(this, it.stringResId, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}