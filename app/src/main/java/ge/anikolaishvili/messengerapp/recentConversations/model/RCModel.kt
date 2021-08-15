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

    private val db = Firebase.database
    private val chats = db.getReference("chats")
    private val users = db.getReference("users")
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

    private fun getFilteredData(data:  Map<String, UserModel>, looking: String) : Map<String, UserModel> {
        var myMap = emptyMap<String, UserModel>().toMutableMap()

        for ((id, user) in data) {
            if (looking != null && looking != "") {
                val username = user.email
                if (username == null || username == "") {
                    myMap[id] = user
                } else {
                    if (!username.contains(looking.toString())) {
                        myMap[id] = user
                    }
                }
            }
        }
        return myMap
    }

    private fun getList(mp: Map<String, String>, of: String?) {

        val ids = mp.keys.toList()
        val chatToUser = mp.entries.associate { (key, value) -> value to key }


        users.get().addOnSuccessListener { it ->
            var data = it.getValue<Map<String, UserModel>>()!!

            data = data.filter { (key, _) ->
                key in ids
            }.onEach { (id, user) ->
                user.uid = id
            }

            data = getFilteredData(data, of.toString())

            data.let { tmpMap ->
                val convIds: List<String> = tmpMap.map { (key, user) -> mp[key].toString() }
                chats.get().addOnSuccessListener { cSnap ->
                    val chatsMap = cSnap.getValue<Map<String, ChatModel>>()?.filter { (uid, _) ->
                        uid in convIds
                    }?.onEach { (chatUid, chat) ->
                        chat.interloc = tmpMap[chatToUser[chatUid]]
                        chat.uid = chatUid
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