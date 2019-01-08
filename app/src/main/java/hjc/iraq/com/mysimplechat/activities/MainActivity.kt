package hjc.iraq.com.mysimplechat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hjc.iraq.com.mysimplechat.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth?= null
    var user: FirebaseUser? = null
    var mAthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mAthListener = FirebaseAuth.AuthStateListener {
            firebaseAuth: FirebaseAuth ->

            user = firebaseAuth.currentUser
            if (user !=null){
                // let go dashboardActivity
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()

            }else{
                Toast.makeText(this,"Not signed In",Toast.LENGTH_LONG).show()

            }
        }


        buttonAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        createActButton.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth?.addAuthStateListener (mAthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAthListener !=null){
            mAuth?.removeAuthStateListener (mAthListener!!)
        }
    }
}
