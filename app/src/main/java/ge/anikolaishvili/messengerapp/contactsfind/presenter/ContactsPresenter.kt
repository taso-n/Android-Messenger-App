package ge.anikolaishvili.messengerapp.contactsfind.presenter

import ge.anikolaishvili.messengerapp.contactsfind.model.ContactsModel
import ge.anikolaishvili.messengerapp.contactsfind.view.IContactsView
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel

class ContactsPresenter(private val view: IContactsView) : IContactsPresenter {
    private val model: ContactsModel = ContactsModel(this)

    // MARK TASO, test
    override fun getContacts(of: String?) {
        model.getContacts(of)
    }

    override fun addConv(chat: ChatModel) {
        model.insertChat(chat)
    }

    // MARK TASO
    override fun didAddConv(conv: ChatModel) {
        view.didAddChat(conv)
    }

    override fun didFetchConv(of: MutableList<UserModel>) {
        view.displayContacts(of)
    }
}