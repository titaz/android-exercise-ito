package lt.ito.mycooking

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import it.ito.mycooking.R
import kotlinx.android.synthetic.main.activity_main.*
import lt.ito.mycooking.di.components.ActivityComponent
import lt.ito.mycooking.di.components.DaggerActivityComponent

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerActivityComponent.builder()
            .activity(this)
            .appComponent(ITOApplication.appComponent(this))
            .build()
        component.inject(this)
        setContentView(R.layout.activity_main)

        bottomNavigation.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) || super.onOptionsItemSelected(item)
    }
}
