package hjc.iraq.com.mysimplechat.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hjc.iraq.com.mysimplechat.R
import hjc.iraq.com.mysimplechat.activities.ChatActivity
import hjc.iraq.com.mysimplechat.activities.ProfileActivity
import hjc.iraq.com.mysimplechat.models.Users
import kotlinx.android.synthetic.main.users_row.view.*




class UsersAdapter(var usersList: List<Users>) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_row, parent, false)
        return UsersViewHolder(view)

    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int) {

        viewHolder.setUsers(usersList[position])


        viewHolder.itemView.setOnClickListener {
            //Create an AlertDialog to prompt users if they want to see profile or send message

            val options = arrayOf("Open Profile", "Send Message")
            val builder = AlertDialog.Builder(viewHolder.view.context)
            builder.setTitle("Select Options")
            builder.setItems(options) { dialogInterface: DialogInterface, i: Int ->
                val userName =  usersList[position].display_name
                val userStat = usersList[position].status
                val profilePic = usersList[position].thumb_image

                if (i == 0) {
                    //open user profile

                    val profileIntent = Intent(it.context, ProfileActivity::class.java)
//                       profileIntent.putExtra("userId",usersList[viewHolder.layoutPosition])
                    profileIntent.putExtra("name", userName)
                    profileIntent.putExtra("status", userStat)
                    profileIntent.putExtra("profile", profilePic)

                    viewHolder.view.context.startActivity(profileIntent)

                } else {

                    //Send Message/ ChatActivity
                    val chatIntent = Intent(viewHolder.view.context, ChatActivity::class.java)
//                        chatIntent.putExtra("userId", userId)
//                    chatIntent.putExtra("userId",usersList[viewHolder.layoutPosition])

                    chatIntent.putExtra("name", userName)
                        chatIntent.putExtra("status", userStat)
                        chatIntent.putExtra("profile", profilePic)
                    viewHolder.view.context.startActivity(chatIntent)


                }

            }
            builder.show()

        }


    }

    inner class UsersViewHolder

        (val view: View) : RecyclerView.ViewHolder(view) {

        init {
            this.view.setOnClickListener {
                val clickedUser = usersList[layoutPosition]

//                val options = arrayOf("Open Profile", "Send Message")
//                val builder = AlertDialog.Builder(view.context)
//                builder.setTitle("Select Options")
//                builder.setItems(options) { dialogInterface: DialogInterface, i: Int ->
//                    var userName = it.userName
//                    var userStat = it.userStatus
//                    var profilePic = it.usersProfile
//
//                    if (i == 0) {
//                        //open user profile
//
//                        val profileIntent = Intent(it.context, ProfileActivity::class.java).apply {
//                            putExtra("userId",usersList[layoutPosition])
//                        }
//                       view.context.startActivity(profileIntent)
//
//                    } else {
//
//                        //Send Message/ ChatActivity
//                        var chatIntent = Intent(view.context, ChatActivity::class.java)
////                        chatIntent.putExtra("userId", userId)
////                        chatIntent.putExtra("name", userName)
////                        chatIntent.putExtra("status", userStat)
////                        chatIntent.putExtra("profile", profilePic)
//                        view.context.startActivity(chatIntent)
//
//
//                    }
//
//                }
//
//
//
//
//
////                val clickId = clickedMove.id
//                //Log.i("Movie Adapter","$clickId")
////
////                val intent = Intent(view.context, ConstraintActivity::class.java)
////
////                intent.putExtra(Consts.imdbID, clickId)
////                view.context.startActivity(intent)
//                builder.show()
//


            }
        }

        fun setUsers(users: Users) {
            view.userName.text = users.display_name
            view.userStatus.text = users.status
            Picasso.get()
                .load(users.thumb_image)
                .into(view.usersProfile)

        }

    }
}