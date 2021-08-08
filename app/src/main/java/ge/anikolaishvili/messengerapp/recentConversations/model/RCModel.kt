package ge.anikolaishvili.messengerapp.recentConversations.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.recentConversations.presenter.RCPresenter

class RCModel (private val presenter: RCPresenter): IRCModel{

    private val database = Firebase.database
    private val chats = database.getReference("chats")
    private val users = database.getReference("users")
    private var auth: FirebaseAuth = Firebase.auth

    override fun getRecentConv(of: String?) {
        Log.d("vikaa", "vaime vika")
        auth.currentUser?.uid?.let { id ->
            users.child(id).get().addOnSuccessListener { us ->
                val foundUser = us.getValue<UserModel>()
                if (foundUser?.chats != null) {
                    getList(foundUser.chats!!, of)
                }
            }.addOnFailureListener {
//                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getList(mp: Map<String, String>, of: String?) {

        val ids = mp.keys.toList()
        val chatToUser = mp.entries.associate { (key, value) -> value to key }


        users.get().addOnSuccessListener { it ->
            var data = it.getValue<Map<String, UserModel>>()!!.filter { (key, _) ->
                key in ids
            }.onEach { (id, user) ->
                user.uid = id
            }

            data = data.filter { (id, user) ->
                if (of != null && of != "") {
                    val username = user.email
                    if (username == null || username == "") {
                        return@filter false
                    } else {
                        return@filter username.contains(of.toString())
                    }
                } else {
                    return@filter true
                }
            }

            data.let { tmpMap ->
                val convIds: List<String> = tmpMap.map { (key, user) -> mp[key].toString() }
                chats.get().addOnSuccessListener { cSnap ->
                    val chatsMap = cSnap.getValue<Map<String, ChatModel>>()?.filter { (uid, _) ->
                        uid in convIds
                    }?.onEach { (chatUid, chat) ->
                        chat.uid = chatUid
                        chat.interloc = tmpMap[chatToUser[chatUid]]
                    }

                    chatsMap?.let {
                        val chats = chatsMap.values.sortedByDescending {
                            it.lastMessage?.dateRec
                        }.toMutableList()
                        presenter.didGetRecentConv(chats)
                    }
                }.addOnFailureListener {
//                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        }.addOnFailureListener {
//            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()

        }
    }

}