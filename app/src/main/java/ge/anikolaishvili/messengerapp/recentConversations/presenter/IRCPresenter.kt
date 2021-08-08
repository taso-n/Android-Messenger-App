package ge.anikolaishvili.messengerapp.recentConversations.presenter

import ge.anikolaishvili.messengerapp.datamodel.ChatModel

interface IRCPresenter {
    fun getRecentConv(of: String? = null)
    fun didGetRecentConv(recentConv: MutableList<ChatModel>)
}