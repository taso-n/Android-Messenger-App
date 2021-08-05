package ge.anikolaishvili.messengerapp.datamodel

import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class ChatModel(
    @get:Exclude
    var uid: String? = null,
    var interloc: UserModel? = null,
    val messages: MutableList<MessageModel>? = null,
    val lastMessage: MessageModel? = null,
) : Parcelable