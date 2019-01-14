package hjc.iraq.com.mysimplechat.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import hjc.iraq.com.mysimplechat.R
import hjc.iraq.com.mysimplechat.models.FriendlyMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.custom_bar_image.view.*

class ChatActivity : AppCompatActivity() {

    var userId: String = ""
    var mFirebaseDatabaseRef: DatabaseReference? = null
    var mFirebaseUser: FirebaseUser? = null

    var mLinearLayoutManager: LinearLayoutManager? = null
    var mFirebaseAdapter: FirebaseRecyclerAdapter<FriendlyMessage, FriendViewHolder>? = null

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        mFirebaseUser = FirebaseAuth.getInstance().currentUser

        userId = intent.getStringExtra("userId")
        val profileImgLink = intent.getStringExtra("profile").toString()
        mLinearLayoutManager = LinearLayoutManager(this)
        mLinearLayoutManager!!.stackFromEnd = true

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowCustomEnabled(true)



        val inflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater

        val actionBarView = inflater.inflate(R.layout.custom_bar_image, null)
        actionBarView.customBarName.text = intent.getStringExtra("name")
        Picasso.get()
            .load(profileImgLink)
            .placeholder(R.drawable.profile_boy)
            .into(actionBarView.customBarCircleImage)

        supportActionBar!!.customView = actionBarView


        mFirebaseDatabaseRef = FirebaseDatabase.getInstance().reference

        mFirebaseAdapter = object : FirebaseRecyclerAdapter<FriendlyMessage,
                FriendViewHolder>(
            FriendlyMessage::class.java,
            R.layout.item_message,
            FriendViewHolder::class.java,
            mFirebaseDatabaseRef!!.child("messages")
        ) {

            override fun populateViewHolder(
                viewHolder: FriendViewHolder?,
                friendlyMessage: FriendlyMessage?,
                position: Int
            ) {

                if (friendlyMessage!!.text != null) {
                    viewHolder!!.bindView(friendlyMessage)

                    val currentUserId = mFirebaseUser!!.uid

                    val isMe: Boolean = friendlyMessage.id!! == currentUserId

                    if (isMe) {
                        //Me to the right side
                        viewHolder.profileImageViewRight!!.visibility = View.VISIBLE
                        viewHolder.profileImageView!!.visibility = View.GONE
                        viewHolder.messageTextView!!.gravity = (Gravity.CENTER_VERTICAL or Gravity.END)
                        viewHolder.messengerTextView!!.gravity = (Gravity.CENTER_VERTICAL or Gravity.END)


                        //Get imageUrl for me!
                        mFirebaseDatabaseRef!!.child("Users")
                            .child(currentUserId)
                            .addValueEventListener(object : ValueEventListener {
                                @SuppressLint("SetTextI18n")
                                override fun onDataChange(data: DataSnapshot) {

                                    val imageUrl = data.child("thumb_image").value.toString()

                                    viewHolder.messageTextView!!.text = friendlyMessage.text.toString()
                                    viewHolder.messengerTextView!!.text =
                                            "I wrote..."



                                    Picasso.get()
                                        .load(imageUrl)
                                        .placeholder(R.drawable.profile_boy)
                                        .into(viewHolder.profileImageViewRight)

                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(this@ChatActivity, error.message, Toast.LENGTH_LONG).show()

                                }
                            })

                    } else {
                        //the other person show imageview to the left side

                        viewHolder.profileImageViewRight!!.visibility = View.GONE
                        viewHolder.profileImageView!!.visibility = View.VISIBLE
                        viewHolder.messageTextView!!.gravity = (Gravity.CENTER_VERTICAL or Gravity.START)
                        viewHolder.messengerTextView!!.gravity = (Gravity.CENTER_VERTICAL or Gravity.START)

                        //Get imageUrl for me!
                        mFirebaseDatabaseRef!!.child("Users")
                            .child(userId)
                            .addValueEventListener(object : ValueEventListener {
                                @SuppressLint("SetTextI18n")
                                override fun onDataChange(data: DataSnapshot) {

                                    val imageUrl = data.child("thumb_image").value.toString()
                                    val displayName = data.child("display_name").value.toString()

                                    viewHolder.messageTextView!!.text = friendlyMessage.text.toString()
                                    viewHolder.messengerTextView?.text =
                                            "$displayName wrote..."

                                    Picasso.get()
                                        .load(imageUrl)
                                        .placeholder(R.drawable.profile_boy)
                                        .into(viewHolder.profileImageView)

                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(this@ChatActivity, error.message, Toast.LENGTH_LONG).show()

                                }
                            })


                    }

                }
            }

        }


        // Set the RecyclerView
        messageRecyclerView.setHasFixedSize(true)
        messageRecyclerView.layoutManager = mLinearLayoutManager
        messageRecyclerView.adapter = mFirebaseAdapter


        sendButton.setOnClickListener {
            if (intent.getStringExtra("name").toString() != "") {
                val currentUsername = intent.getStringExtra("name")
                val mCurrentUserId = mFirebaseUser!!.uid


                val friendlyMessage = FriendlyMessage(
                    mCurrentUserId,
                    messageEdt.text.toString().trim(),
                    currentUsername.toString().trim()
                )

                mFirebaseDatabaseRef!!.child("messages")
                    .push().setValue(friendlyMessage)

                messageEdt.setText("")


            }
        }


    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var messageTextView: TextView? = null
        var messengerTextView: TextView? = null
        var profileImageView: CircleImageView? = null
        var profileImageViewRight: CircleImageView? = null

        fun bindView(friendlyMessage: FriendlyMessage) {

            messageTextView = itemView.findViewById(R.id.messageTextview)
            messengerTextView = itemView.findViewById(R.id.messengerTextview)
            profileImageView = itemView.findViewById(R.id.messengerImageView)
            profileImageViewRight = itemView.findViewById(R.id.messengerImageViewRight)

            messengerTextView!!.text = friendlyMessage.name
            messageTextView!!.text = friendlyMessage.text

        }
    }
}
