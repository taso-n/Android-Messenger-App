package ge.anikolaishvili.messengerapp.textsetting.model

import ge.anikolaishvili.messengerapp.datamodel.MessageModel

interface TextSettingModel {
    fun sendText(with: MessageModel)
}