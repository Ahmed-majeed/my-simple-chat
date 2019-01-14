package hjc.iraq.com.mysimplechat.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hjc.iraq.com.mysimplechat.R
import hjc.iraq.com.mysimplechat.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val sectionAdapter: SectionPagerAdapter?
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.title="Dashboard"

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        dashViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashViewPagerId)
        mainTabs.setTabTextColors(Color.WHITE,Color.GREEN)

//        if (intent.getStringExtra("name") != null) {
//            val username = intent.getStringExtra("name")
//            Toast.makeText(this, username, Toast.LENGTH_LONG).show()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         super.onOptionsItemSelected(item)
        if (item != null){
            if (item.itemId == R.id.logoutId){
                // Log the user out
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }

                if (item.itemId == R.id.settingsId){
                    // Take use to settingsActivity
                    startActivity(Intent(this,SettingsActivity::class.java))
                }

        }
        return true
    }
}
