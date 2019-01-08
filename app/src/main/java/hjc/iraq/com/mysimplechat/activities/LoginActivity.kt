package hjc.iraq.com.mysimplechat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import hjc.iraq.com.mysimplechat.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()


        this.loginButtonId.setOnClickListener {


            val email = loginEmailEt.text.toString().trim()
            val password = loginPasswordEt.text.toString().trim()

            if (email.isNotEmpty() || password.isNotEmpty()){

                loginUser(email, password)

            } else {
                Toast.makeText(this, "Sorry,  Login Failed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {

        mAuth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->

                if (task.isSuccessful) {
                    // ahmed@me.com split---> [ahmed],[me.com]

                    val username = email.split("@")[0]
                    val dashboardIntent = Intent(this, DashboardActivity::class.java)
                    dashboardIntent.putExtra("name", username)
                    startActivity(dashboardIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Login Field !!", Toast.LENGTH_LONG).show()
                }
            }

    }
}
