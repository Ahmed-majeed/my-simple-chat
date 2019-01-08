package hjc.iraq.com.mysimplechat.activities


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import hjc.iraq.com.mysimplechat.Consts
import hjc.iraq.com.mysimplechat.R
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.ByteArrayOutputStream
import java.io.File

class SettingsActivity : AppCompatActivity() {

    var mDatabase: DatabaseReference? = null
    var mCurrentUser: FirebaseUser? = null
    var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mCurrentUser = FirebaseAuth.getInstance().currentUser
        mStorageRef = FirebaseStorage.getInstance().reference

        val userId = mCurrentUser!!.uid
        mDatabase = FirebaseDatabase.getInstance().reference
            .child("Users")
            .child(userId)

        mDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(dataBaseErrorSnapshot: DatabaseError) {

                Toast.makeText(this@SettingsActivity, dataBaseErrorSnapshot.message, Toast.LENGTH_LONG).show()

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val displayName = dataSnapshot.child("display_name").value
                val image = dataSnapshot.child("image").value.toString()
                val userStatus = dataSnapshot.child("status").value
                val thumbanil = dataSnapshot.child("thumb_image").value

                settingsDisplayName.text = displayName.toString()
                settingsStatusText.text = userStatus.toString()

                if (!image!!.equals("default")) {
                    Picasso.get()
                        // .placeholder(image)
                        .load(image)
                        .into(settingsProfileID)


                }


            }

        })

        settingsChangeStatus.setOnClickListener {
            val intent = Intent(this, StatusActivity::class.java)
            intent.putExtra("status", settingsStatusText.text.toString().trim())
            startActivity(intent)
        }

        settingsChangeImgBtn.setOnClickListener {
            val galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(galleryIntent, "SELECT_IMAGE"), Consts.GALLERY_ID)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == Consts.GALLERY_ID && resultCode == Activity.RESULT_OK) {

            val image: Uri = data!!.data
            CropImage.activity(image)
                .setAspectRatio(1, 1)
                .start(this)
        }


        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)
            if (resultCode === Activity.RESULT_OK) {
                val resultUri = result.uri

                val userId = mCurrentUser?.uid
                val thumbFile = File(resultUri.path)
                val thumbBitmap = Compressor(this)
                    .setMaxWidth(200)
                    .setMaxHeight(200)
                    .setQuality(65)
                    .compressToBitmap(thumbFile)
                // we upload images to firebase

                val byteArray = ByteArrayOutputStream()
                thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)

                val thumbByteArray: ByteArray
                thumbByteArray = byteArray.toByteArray()

                val filePath = mStorageRef?.child("chat_profile_images")
                    ?.child(userId + ".jpg")

                // create another directory thumb images compress(smaller, compressed image)

                val thumbFilePath = mStorageRef?.child("chat_profile_images")
                    ?.child("thumbs")
                    ?.child(userId + ".jpg")



                filePath?.putFile(resultUri)
                    ?.addOnCompleteListener { task: Task<UploadTask.TaskSnapshot> ->
                        if (task.isSuccessful) {
                            //let get the pic url

                            val downloadUrl = resultUri.toString()

                            // upload task
                            val uploadTask: UploadTask = thumbFilePath!!.putBytes(thumbByteArray)

                            uploadTask.addOnCompleteListener {

                                    task: Task<UploadTask.TaskSnapshot> ->
                                val thumbUrl = resultUri.toString()


                                if (task.isSuccessful) {


                                    val updateObj = HashMap<String, Any>()
                                    updateObj.put("image", downloadUrl)
                                    updateObj.put("thumb_image", thumbUrl)

                                    // we save profile image

                                    mDatabase?.updateChildren(updateObj)
                                        ?.addOnCompleteListener { task: Task<Void> ->

                                            if (task.isSuccessful) {
                                                Toast.makeText(this, "Profile Image saved", Toast.LENGTH_LONG).show()

                                            } else {

                                            }
                                        }

                                } else {

                                }
                            }
                        }

                    }
            }

        }
//        super.onActivityResult(requestCode, resultCode, data)
    }
}

