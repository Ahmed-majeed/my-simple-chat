package hjc.iraq.com.mysimplechat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import hjc.iraq.com.mysimplechat.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()

        accountCreateActBtn.setOnClickListener {
            val email = accountEmailEt.text.toString().trim()
            val password = accountPasswordEt.text.toString().trim()
            val displayName = accountDisplayNameEt.text.toString().trim()

            if (email.isNotEmpty() || password.isNotEmpty() || displayName.isNotEmpty()) {
                createAccount(email, password, displayName)
            } else {
                Toast.makeText(this, "Plase Fill Out The Fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun createAccount(email: String, password: String, displayName: String) {

        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task: Task<AuthResult> ->

                if (task.isSuccessful) {
                    val currentUser = mAuth?.currentUser
                    val userId = currentUser?.uid

                    mDatabase = userId?.let(
                        FirebaseDatabase.getInstance().reference
                            .child("Users")::child
                    )

                    /*
                       Users
                          -994rroddiiAAdl
                             - ahmed
                             - "hello there"
                             - "image url ..."
                     */

                    val userObject = HashMap<String, String>()
                    userObject.put("display_name", displayName)
                    userObject.put("status", "Hello There ....")
                    userObject.put("image", "default")
                    userObject.put("thumb_image", "default")

                    mDatabase?.setValue(userObject)?.addOnCompleteListener {

                            task: Task<Void> ->
                        if (task.isSuccessful) {

                            val dashboardIntent = Intent(this, DashboardActivity::class.java)
                            dashboardIntent.putExtra("name",displayName)
                            startActivity(dashboardIntent)
                            finish()

//                            Toast.makeText(this, "User Created!", Toast.LENGTH_LONG).show()

                        } else {

                            Toast.makeText(this, "User Not Created", Toast.LENGTH_LONG).show()

                        }
                    }

                } else {

                }
            }

    }
}
