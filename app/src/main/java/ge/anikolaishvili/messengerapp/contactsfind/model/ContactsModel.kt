package ge.anikolaishvili.messengerapp.contactsfind.model

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.anikolaishvili.messengerapp.contactsfind.presenter.ContactsPresenter
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel

class ContactsModel(private val presenter: ContactsPresenter) : IContactsModel {
    private val db = Firebase.database
    private val auth = Firebase.auth

    private val users = db.getReference("users")
    private val chats = db.getReference("chats")

    override fun getContacts(of: String?) {
        users.get().addOnSuccessListener {
            var users = it.getValue<MutableMap<String, UserModel>>()

            if (of == null || of.isEmpty()) {
                if (users != null) users!!.clear()
            }

            if (users != null) {
                users = users.filter { (_, usr) ->
                    val email = usr.email
                    if (of.isNullOrBlank()) return@filter true
                    if (email != null && email != "") return@filter email.contains(of) else return@filter false
                } as MutableMap<String, UserModel>?
            }

            users?.apply {
                remove(auth.currentUser?.uid)
                forEach { (key, user) ->
                    user.uid = key
                }
                values.toMutableList().let {
                    presenter.didFetchConv(it)
                }
            }
        }
    }

    override fun insertChat(chat: ChatModel) {
        if (chat.interloc == null) return

        val keyValues = chats.push().key ?: return

        val childUpdates = hashMapOf<String, Any>(
            "${chat.interloc!!.uid!!}/chats/${auth.currentUser!!.uid}" to keyValues,
            "${auth.currentUser!!.uid}/chats/${chat.interloc!!.uid!!}" to keyValues
        )

        users.updateChildren(childUpdates).addOnSuccessListener {
            chat.uid = keyValues
            presenter.didAddConv(chat)
        }
    }

    private fun getFilteredUsers(map: MutableMap<String, UserModel>) {

    }
}
