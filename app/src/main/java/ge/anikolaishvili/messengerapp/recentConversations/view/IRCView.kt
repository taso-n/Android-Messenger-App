package ge.anikolaishvili.messengerapp.recentConversations.view

import ge.anikolaishvili.messengerapp.datamodel.ChatModel

interface IRCView {
    fun displayChats(recentConv: MutableList<ChatModel>)
}