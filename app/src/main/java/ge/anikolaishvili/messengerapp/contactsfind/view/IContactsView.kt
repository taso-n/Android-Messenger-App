package ge.anikolaishvili.messengerapp.contactsfind.view

import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface IContactsView {
    fun didAddChat(of: ChatModel)
    fun displayContacts(friends: MutableList<UserModel>)
}