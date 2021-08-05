package ge.anikolaishvili.messengerapp.contactsfind.model

import ge.anikolaishvili.messengerapp.datamodel.ChatModel

interface IContactsModel {
    fun getContacts(of: String? = null)
    fun insertChat(with: ChatModel)
}