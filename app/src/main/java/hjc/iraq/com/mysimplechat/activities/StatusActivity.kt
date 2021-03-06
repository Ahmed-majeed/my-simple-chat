package hjc.iraq.com.mysimplechat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import hjc.iraq.com.mysimplechat.R
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity : AppCompatActivity() {

    var mDatabase: DatabaseReference? = null
    var mCurrentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        supportActionBar?.title = "Status"

        if (intent.extras != null) {
            val oldStatus = intent.getStringExtra("status")
            statusUpdateEt.setText(oldStatus)

        }
        if (intent.extras == null) {
            statusUpdateEt.setText("Enter Your New Status")
        }
        statusUpdateBtn.setOnClickListener {
            mCurrentUser = FirebaseAuth.getInstance().currentUser
            val userId = mCurrentUser!!.uid

            mDatabase = FirebaseDatabase.getInstance().reference
                .child("Users")
                .child(userId)

            var status = statusUpdateEt.text.toString().trim()

            mDatabase?.child("status")
                ?.setValue(status)?.addOnCompleteListener { task: Task<Void> ->

                    if (task.isSuccessful) {
                        Toast.makeText(this, "Status Updated Successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, SettingsActivity::class.java))
                    } else {
                        Toast.makeText(this, "Not Status Updated ", Toast.LENGTH_LONG).show()

                    }


                }
        }
    }
}
