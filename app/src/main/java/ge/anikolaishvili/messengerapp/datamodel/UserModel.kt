package ge.anikolaishvili.messengerapp.datamodel

import android.graphics.Bitmap
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class UserModel(
    @get:Exclude
    var uid: String? = null,
    var email: String? = null,
    var profession: String? = null,
    var chats: Map<String, String>? = null,
) : Parcelable {

}