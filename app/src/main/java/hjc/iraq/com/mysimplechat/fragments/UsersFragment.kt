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


        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)

        mUserDatabase = FirebaseDatabase.getInstance().reference.child("Users")

        userRecyclerViewId.setHasFixedSize(true)


        userRecyclerViewId.layoutManager = linearLayoutManager
        userRecyclerViewId.adapter = UsersAdapter(mUserDatabase!!, context!!)


    }
}




