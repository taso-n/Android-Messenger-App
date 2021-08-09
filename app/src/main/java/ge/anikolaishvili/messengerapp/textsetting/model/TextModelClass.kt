package ge.anikolaishvili.messengerapp.textsetting.model

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.anikolaishvili.messengerapp.datamodel.MessageModel
import ge.anikolaishvili.messengerapp.textsetting.presenter.PresenterText

class TextModelClass(private val presenter: PresenterText, private val textUid: String) : TextSettingModel {
    private val auth = Firebase.auth
    private val db = Firebase.database
    private val msgs = db.getReference("messages")
    private val chats = db.getReference("chats")

    init {
        msgs.child(textUid).addValueEventListener(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val texts = snapshot.getValue<MutableMap<String, MessageModel>>()

                    texts?.apply {
                        forEach { (_, text) ->
                            text.gotText = text.sender != auth.currentUser?.uid
                            text.chatUid = textUid
                        }
                        presenter.chatDidUpdate(
                            texts.values.sortedBy {
                                it.dateRec
                            }.toMutableList()
                        )
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // do nothing
                }
            }
        )
    }

    override fun sendText(with: MessageModel) {
        if (with != null) {
            with.sender = auth.currentUser?.uid

            val chatNode = msgs.child(with.chatUid!!)
            val msgKey = chatNode.push().key
            Log.d("VIKA DEBUG", "got to sendText in TextModelClass")

            if (msgKey != null) {
                chatNode.child(msgKey).setValue(with)
                chats.child(with.chatUid!!).child("lastMessage").setValue(with)
            } else {
                Log.d("VIKA DEBUG", "msg key is NULL")
            }
        }
    }
}
