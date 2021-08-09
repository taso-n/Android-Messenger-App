package ge.anikolaishvili.messengerapp.textsetting.view

import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.MessageModel

interface TextSettingView {
    fun show(messages: MutableList<MessageModel>)
    fun display(chat: ChatModel)
}