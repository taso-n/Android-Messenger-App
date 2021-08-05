package ge.anikolaishvili.messengerapp.contactsfind.presenter

import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel

interface IContactsPresenter {
    fun getContacts(of: String? = null)
    fun addConv(chat: ChatModel)
    fun didAddConv(conv: ChatModel)
    fun didFetchConv(of: MutableList<UserModel>)
}