package ge.anikolaishvili.messengerapp.datamodel

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@IgnoreExtraProperties
@Parcelize
class MessageModel(
    @get:Exclude
    var chatUid: String? = null,
    @get:Exclude
    var gotText: Boolean? = true,
    var sender: String? = null,
    val dateRec: Long? = Calendar.getInstance().timeInMillis,
    val content: String? = null,
) : Parcelable {
}