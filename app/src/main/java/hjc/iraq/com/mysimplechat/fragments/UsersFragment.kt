package hjc.iraq.com.mysimplechat.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import hjc.iraq.com.mysimplechat.R
import hjc.iraq.com.mysimplechat.adapters.UsersAdapter
import kotlinx.android.synthetic.main.fragment_users.*


class UsersFragment : Fragment() {
    var mUserDatabase: DatabaseReference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? { return inflater.inflate(R.layout.fragment_users, container, false)
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//
//        val usersRef = FirebaseDatabase.getInstance().reference.child("Users")
//        usersRef.addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context,error.message,Toast.LENGTH_LONG).show()
//            }
//
//            @SuppressLint("WrongConstant")
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                val users: MutableList<Users> = mutableListOf()
//                snapshot.children.forEach { child ->
//
//                    val user = child.getValue(Users::class.java)
//
//                    user?.let { user ->
//                        users.add(user)
//                    }
//
//                }
//                userRecyclerViewId.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//                userRecyclerViewId.adapter = UsersAdapter(users)
//            }
//
//        })

        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

        mUserDatabase = FirebaseDatabase.getInstance().reference.child("Users")

        userRecyclerViewId.setHasFixedSize(true)


        userRecyclerViewId.layoutManager = linearLayoutManager
        userRecyclerViewId.adapter = UsersAdapter(mUserDatabase!!, context!!)

//        val linerLayoutManger = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        mUserDatabase = FirebaseDatabase.getInstance().reference.child("Users")
//        userRecyclerViewId.setHasFixedSize(true)
//        userRecyclerViewId.layoutManager = linerLayoutManger
//        userRecyclerViewId.adapter = UsersAdapter(mUserDatabase!!, context!!)

    }
}




