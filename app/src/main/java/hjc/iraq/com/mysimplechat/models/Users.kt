package hjc.iraq.com.mysimplechat.models

import android.os.Parcel
import android.os.Parcelable


class Users() : Parcelable {
    var display_name: String? = null

    var image: String? = null

    var thumb_image: String? = null

    var status: String? = null

    constructor(
        display_name: String, image: String,
        thumb_image: String, user_status: String
    ) : this() {
        this.display_name = display_name
        this.image = image
        this.thumb_image = thumb_image
        this.status = user_status

    }

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Users> = object : Parcelable.Creator<Users> {
            override fun createFromParcel(source: Parcel): Users = Users(source)
            override fun newArray(size: Int): Array<Users?> = arrayOfNulls(size)
        }
    }
}
