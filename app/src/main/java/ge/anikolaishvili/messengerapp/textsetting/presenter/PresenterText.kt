package ge.anikolaishvili.messengerapp.textsetting.presenter

import android.util.Log
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.MessageModel
import ge.anikolaishvili.messengerapp.textsetting.model.TextModelClass
import ge.anikolaishvili.messengerapp.textsetting.view.TextSettingView

class PresenterText(private val view: TextSettingView, chatUid: String) : TextSettingPresenter {
    private val textModel = TextModelClass(this, chatUid)

    override fun sendMessageModel(model: MessageModel) {
        Log.d("TASO DEBUG", model.content.toString())
        this.textModel.sendText(model)
    }

    override fun didFetchChat(chatDescription: ChatModel) {
        view.display(chatDescription)
    }

    override fun chatDidUpdate(messages: MutableList<MessageModel>) {
        view.show(messages)
    }
}