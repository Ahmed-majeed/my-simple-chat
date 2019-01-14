package hjc.iraq.com.mysimplechat.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import hjc.iraq.com.mysimplechat.R
import hjc.iraq.com.mysimplechat.activities.ChatActivity
import hjc.iraq.com.mysimplechat.activities.ProfileActivity
import hjc.iraq.com.mysimplechat.models.Users


class UsersAdapter(databaseQuery: DatabaseReference, var context: Context)
    : FirebaseRecyclerAdapter<Users, UsersAdapter.ViewHolder>(
    Users::class.java,
    R.layout.users_row,
    UsersAdapter.ViewHolder::class.java,
    databaseQuery

){
    override fun populateViewHolder(viewHolder: UsersAdapter.ViewHolder?, user: Users?, position: Int) {

        val userId = getRef(position).key // the unique firebase keyid for this current user!
        viewHolder!!.bindView(user!!)

        viewHolder.itemView.setOnClickListener {
            //Create an AlertDialog to prompt users if they want to see profile or send message
            val options = arrayOf("Open Profile", "Send Message")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Selecte Options")
//            builder.setItems(options, DialogInterface.OnClickListener { dialogInterface, i ->
            builder.setItems(options) { _: DialogInterface, i: Int ->
                val userName = viewHolder.userNameTxt.toString()
                val userStat = viewHolder.userStatusTxt.toString()
                val profilePic = viewHolder.userProfilePicLink.toString()

                if (i == 0) {
                    //open user profile

                    val profileIntent = Intent(context, ProfileActivity::class.java)
                    profileIntent.putExtra("userId", userId)
                    context.startActivity(profileIntent)


                } else {
                    //Send Message/ ChatActivity
                    val chatIntent = Intent(context, ChatActivity::class.java)
                    chatIntent.putExtra("userId", userId)
                    chatIntent.putExtra("name", userName)
                    chatIntent.putExtra("status", userStat)
                    chatIntent.putExtra("profile", profilePic)
                    context.startActivity(chatIntent)
                }

            }
//            )

            builder.show()

        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userNameTxt: String? = null
        var userStatusTxt: String? = null
        var userProfilePicLink: String? = null


        fun bindView(user: Users) {
            val userName = itemView.findViewById<TextView>(R.id.userName)
            val userStatus = itemView.findViewById<TextView>(R.id.userStatus)
            val userProfilePic = itemView.findViewById<CircleImageView>(R.id.usersProfile)

            //set the strings so we can pass in the intent
            userNameTxt = user.display_name
            userStatusTxt = user.status
            userProfilePicLink = user.thumb_image

            userName.text = user.display_name
            userStatus.text = user.status

            Picasso.get()
                .load(userProfilePicLink)
                .placeholder(R.drawable.profile_boy)
                .into(userProfilePic)

        }

    }

}


//
//import android.content.DialogInterface
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AlertDialog
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.database.DatabaseReference
//import com.squareup.picasso.Picasso
//import hjc.iraq.com.mysimplechat.R
//import hjc.iraq.com.mysimplechat.activities.ChatActivity
//import hjc.iraq.com.mysimplechat.activities.ProfileActivity
//import hjc.iraq.com.mysimplechat.models.Users
//import kotlinx.android.synthetic.main.users_row.view.*
//
//
//
//
//class UsersAdapter(var usersList: List<Users>) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
//
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.users_row, parent, false)
//        return UsersViewHolder(view)
//
//    }
//
//    override fun getItemCount(): Int {
//        return usersList.size
//    }
//
//    override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int) {
//
//        viewHolder.setUsers(usersList[position])
//
//
//        viewHolder.itemView.setOnClickListener {
//            //Create an AlertDialog to prompt users if they want to see profile or send message
//
//            val options = arrayOf("Open Profile", "Send Message")
//            val builder = AlertDialog.Builder(viewHolder.view.context)
//            builder.setTitle("Select Options")
//            builder.setItems(options) { dialogInterface: DialogInterface, i: Int ->
//                val userName =  usersList[position].display_name
//                val userStat = usersList[position].status
//                val profilePic = usersList[position].thumb_image
//
//                if (i == 0) {
//                    //open user profile
//
//                    val profileIntent = Intent(it.context, ProfileActivity::class.java)
////                       profileIntent.putExtra("userId",usersList[viewHolder.layoutPosition])
//                    profileIntent.putExtra("name", userName)
//                    profileIntent.putExtra("status", userStat)
//                    profileIntent.putExtra("profile", profilePic)
//
//                    viewHolder.view.context.startActivity(profileIntent)
//
//                } else {
//
//                    //Send MessageAdapter/ ChatActivity
//                    val chatIntent = Intent(viewHolder.view.context, ChatActivity::class.java)
////                        chatIntent.putExtra("userId", userId)
//                    chatIntent.putExtra("userId",usersList[viewHolder.layoutPosition])
//                    chatIntent.putExtra("name", userName)
//                        chatIntent.putExtra("status", userStat)
//                        chatIntent.putExtra("profile", profilePic)
//                    viewHolder.view.context.startActivity(chatIntent)
//
//
//                }
//
//            }
//            builder.show()
//
//        }
//
//
//    }
//
//    inner class UsersViewHolder
//
//        (val view: View) : RecyclerView.ViewHolder(view) {
//
//        init {
//            this.view.setOnClickListener {
//                val clickedUser = usersList[layoutPosition]
//
//
//            }
//        }
//
//        fun setUsers(users: Users) {
//            view.userName.text = users.display_name
//            view.userStatus.text = users.status
//            Picasso.get()
//                .load(users.thumb_image)
//                .into(view.usersProfile)
//
//        }
//
//    }
//}