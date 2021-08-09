package ge.anikolaishvili.messengerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import ge.anikolaishvili.messengerapp.contactsfind.view.ContactsActivity
import ge.anikolaishvili.messengerapp.databinding.ActivityMainBinding
import ge.anikolaishvili.messengerapp.recentConversations.view.RCView
import ge.anikolaishvili.messengerapp.updateSettings.view.SettingsView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabItemSelectedListener: NavigationBarView.OnItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomActions()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.wrapper, RCView())
            .commit()
        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener(tabItemSelectedListener)


        binding.fActionButton.setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }
    }

    private fun initBottomActions() {
        tabItemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->

            val fragment: Fragment? = when(item.itemId) {
                R.id.bottom_home ->  RCView()
                R.id.bottom_settings -> SettingsView()
                else -> null
            }

            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.wrapper, fragment).commit()
            }

            true
        }
    }


}