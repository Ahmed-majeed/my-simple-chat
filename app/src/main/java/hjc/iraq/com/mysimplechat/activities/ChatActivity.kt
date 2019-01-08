package hjc.iraq.com.mysimplechat.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hjc.iraq.com.mysimplechat.R

class ChatActivity : AppCompatActivity() {

//    var userId: String? = null
//    var mFirebaseDatabaseRef: DatabaseReference? = null
//    var mFirebaseUser: FirebaseUser? = null
//
//    var mLinearLayoutManager: LinearLayoutManager? = null
//    var mFirebaseAdapter: FirebaseRecyclerAdapter<FriendlyMessage, MessageViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

//        mFirebaseUser = FirebaseAuth.getInstance().currentUser
//
//        userId = intent.extras.getString("userId")
//        var profileImgLink = intent.extras.get("profile").toString()
//        mLinearLayoutManager = LinearLayoutManager(this)
//        mLinearLayoutManager?.stackFromEnd = true
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowCustomEnabled(true)
//
//        var inflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)
//                as LayoutInflater
//
//        var actionBarView = inflater.inflate(R.layout.custom_bar_image, null)
//        actionBarView.customBarName.text = intent.extras.getString("name")
//        Picasso.get()
//            .load(profileImgLink)
//            .placeholder(R.drawable.profile_boy)
//            .into(actionBarView.customBarCircleImage)
//
//        supportActionBar?.customView = actionBarView
//
//
//
//
//        mFirebaseDatabaseRef = FirebaseDatabase.getInstance().reference
//
//        mFirebaseAdapter =object (databaseQuery: DatabaseReference, var context: Context)  : FirebaseRecyclerAdapter<FriendlyMessage,
//                MessageViewHolder>(
//            FirebaseRecyclerOptions.Builder<FriendlyMessage>().setQuery(databaseQuery, Users::class.java).build()
//            ) {
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
//            }
//
//
//
//            override fun onBindViewHolder(viewHolder: MessageViewHolder, position: Int, friendlyMessage: FriendlyMessage){
//                if (friendlyMessage?.text != null) {
//                    viewHolder?.bindView(friendlyMessage)
//
//                    val currentUserId = mFirebaseUser?.uid
//
//                    val isMe: Boolean = friendlyMessage?.id?.equals(currentUserId)
//
//                    if (isMe) {
//                        //Me to the right side
//                        viewHolder.profileImageViewRight?.visibility = View.VISIBLE
//                        viewHolder.profileImageView?.visibility = View.GONE
//                        viewHolder.messageTextView?.gravity = (Gravity.CENTER_VERTICAL or Gravity.RIGHT)
//                        viewHolder.messengerTextView?.gravity = (Gravity.CENTER_VERTICAL or  Gravity.RIGHT)
//
//
//                        //Get imageUrl for me!
//                        mFirebaseDatabaseRef?.child("Users")
//                            .child(currentUserId)
//                            .addValueEventListener( object: ValueEventListener {
//                                override fun onDataChange(data: DataSnapshot) {
//
//                                    var imageUrl = data?.child("thumb_image").value.toString()
//                                    var displayName = data?.child("display_name").value
//
//                                    viewHolder.messengerTextView?.text =
//                                            "I wrote..."
//
//                                    Picasso.get()
//                                        .load(imageUrl)
//                                        .placeholder(R.drawable.profile_boy)
//                                        .into(viewHolder.profileImageViewRight)
//                                }
//
//                                override fun onCancelled(error: DatabaseError) {
//                                }
//
//
//                            })
//
//                    }else {
//                        //the other person show imageview to the left side
//
//                        viewHolder.profileImageViewRight?.visibility = View.GONE
//                        viewHolder.profileImageView?.visibility = View.VISIBLE
//                        viewHolder.messageTextView?.gravity = (Gravity.CENTER_VERTICAL or Gravity.LEFT)
//                        viewHolder.messengerTextView?.gravity = (Gravity.CENTER_VERTICAL or  Gravity.LEFT)
//
//
//                        //Get imageUrl for me!
//                        mFirebaseDatabaseRef?.child("Users")
//                            .child(userId?)
//                            .addValueEventListener( object: ValueEventListener{
//                                override fun onDataChange(data: DataSnapshot) {
//
//
//                                    var imageUrl = data?.child("thumb_image").value.toString()
//                                    var displayName = data?.child("display_name").value.toString()
//
//                                    viewHolder.messengerTextView?.text =
//                                            "$displayName wrote..."
//
//                                    Picasso.get()
//                                        .load(imageUrl)
//                                        .placeholder(R.drawable.profile_boy)
//                                        .into(viewHolder.profileImageView)
//                                }
//
//                                override fun onCancelled(error: DatabaseError) {
//                                }
//
//
//                            })
//
//
//                    }
//
//                }
//            }
//
//        }
//
//        // Set the RecyclerView
//        messageRecyclerView.layoutManager = mLinearLayoutManager
//        messageRecyclerView.adapter = mFirebaseAdapter
//
//
//        sendButton.setOnClickListener {
//            if (!intent.extras.get("name").toString().equals("")) {
//                val currentUsername = intent.extras.get("name")
//                val mCurrentUserId = mFirebaseUser?.uid
//
//
//                val friendlyMessage = FriendlyMessage(mCurrentUserId,
//                    messageEdt.text.toString().trim(),
//                    currentUsername.toString().trim())
//
//                mFirebaseDatabaseRef?.child("messages")
//                    .push().setValue(friendlyMessage)
//
//                messageEdt.setText("")
//
//
//            }
//        }
//
//
//    }
//
//    class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        var messageTextView: TextView? = null
//        var messengerTextView: TextView? = null
//        var profileImageView: CircleImageView? = null
//        var profileImageViewRight: CircleImageView? = null
//
//        fun bindView(friendlyMessage: FriendlyMessage){
//
//            messageTextView = itemView.findViewById(R.id.messageTextview)
//            messengerTextView = itemView.findViewById(R.id.messengerTextview)
//            profileImageView = itemView.findViewById(R.id.messengerImageView)
////            profileImageViewRight = itemView.findViewById(R.id.messengerImageViewRight)
//
//            messengerTextView?.text = friendlyMessage.name
//            messageTextView?.text = friendlyMessage.text
//
//        }
//    }
}

