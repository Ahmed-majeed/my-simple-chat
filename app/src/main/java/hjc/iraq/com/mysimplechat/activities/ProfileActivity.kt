package hjc.iraq.com.mysimplechat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import hjc.iraq.com.mysimplechat.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    var mCurrentUser: FirebaseUser? = null
    var mUsersDatabase: DatabaseReference? = null
    var userId: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
//
//        supportActionBar!!.title = "Profile"
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//
//        if (intent.extras !=null){
//            mCurrentUser = FirebaseAuth.getInstance().currentUser
//            mUsersDatabase = FirebaseDatabase.getInstance().reference.child("Users")
////                .child("userId")
//
//            setUpProfile()
//        }
//
//    }
//
//    private fun setUpProfile() {
//
//        mUsersDatabase?.addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(databaseError: DatabaseError) {
//                Toast.makeText(this@ProfileActivity,databaseError.message,Toast.LENGTH_LONG).show()
//            }
//
//            override fun onDataChange(dataSnapshot : DataSnapshot) {
//
//                val displayName = intent.getStringExtra("name")
//                val status = intent.getStringExtra("status")
//                val image = intent.getStringExtra("profile")
//
////                val displayName = dataSnapshot.child("display_name").value.toString()
////                val status = dataSnapshot.child("status").value.toString()
////                val image = dataSnapshot.child("image").value.toString()
//
//                profileName.text = displayName
//                profileStatus.text = status
//
//                Picasso.get()
//                    .load(image)
//                    .placeholder(R.drawable.happy_woman)
//                    .into(profilePicture)
//
//
//            }
//
//        })
//    }
//
//
//}
//

        supportActionBar!!.title = "Profile"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (intent.extras != null) {
            userId = intent.getStringExtra("userId").toString()

            mCurrentUser = FirebaseAuth.getInstance().currentUser
            mUsersDatabase = FirebaseDatabase.getInstance().reference.child("Users")
                .child(userId!!)


            setUpProfile()
        }

    }

    private fun setUpProfile() {

        mUsersDatabase!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val displayName = dataSnapshot.child("display_name").value.toString()
                val status = dataSnapshot.child("status").value.toString()
                val image = dataSnapshot.child("image").value.toString()

                profileName.text = displayName
                profileStatus.text = status

                Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.happy_woman)
                    .into(profilePicture)


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@ProfileActivity,databaseError.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}
