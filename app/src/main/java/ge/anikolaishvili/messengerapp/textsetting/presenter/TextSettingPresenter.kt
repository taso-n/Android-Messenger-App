package ge.anikolaishvili.messengerapp.textsetting.presenter

import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.MessageModel

interface TextSettingPresenter {
    fun sendMessageModel(model: MessageModel)
    fun didFetchChat(chatDescription: ChatModel)
    fun chatDidUpdate(messages: MutableList<MessageModel>)
}