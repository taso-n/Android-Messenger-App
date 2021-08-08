package ge.anikolaishvili.messengerapp.recentConversations.model

interface IRCModel {
    fun getRecentConv(of: String? = null)
}