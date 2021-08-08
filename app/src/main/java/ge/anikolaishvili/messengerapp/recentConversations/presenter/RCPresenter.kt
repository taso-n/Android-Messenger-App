package ge.anikolaishvili.messengerapp.recentConversations.presenter

import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.recentConversations.model.RCModel
import ge.anikolaishvili.messengerapp.recentConversations.view.RCView

class RCPresenter(private val view: RCView): IRCPresenter {

    private val model = RCModel(this)

    override fun getRecentConv(of: String?) {
        model.getRecentConv(of)
    }

    override fun didGetRecentConv(recentConv: MutableList<ChatModel>) {
       view.displayChats(recentConv)
    }
}