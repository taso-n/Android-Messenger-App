package ge.anikolaishvili.messengerapp.textsetting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ge.anikolaishvili.messengerapp.databinding.ConversationChatBinding
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.MessageModel
import ge.anikolaishvili.messengerapp.textsetting.presenter.PresenterText
import ge.anikolaishvili.messengerapp.textsetting.TextAdapter
import ge.anikolaishvili.messengerapp.textsetting.presenter.TextSettingPresenter

class QuickTextActivity : AppCompatActivity(), TextSettingView {
    private var emptyStr = ""

    private lateinit var binding: ConversationChatBinding
    private lateinit var adapter: TextAdapter
    private lateinit var presenter: TextSettingPresenter

    private lateinit var chatModel: ChatModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ConversationChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatModel = intent.getParcelableExtra("ge.anikolaishvili.messengerapp.putextra")!!
        presenter = PresenterText(this, chatModel.uid!!)
        binding.personTalkTo.text = chatModel.interloc?.email
        binding.personProfession.text = chatModel.interloc?.profession
        binding.messagesIn.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = TextAdapter()
        adapter.push(chatModel.messages ?: mutableListOf())
        binding.messagesIn.adapter = adapter

        binding.sendButton.setOnClickListener {
            val messageText = binding.textMessage.text.toString()
            binding.textMessage.setText(emptyStr)
            if (messageText != emptyStr) {
                presenter.sendMessageModel(MessageModel(chatUid = chatModel.uid!!, content = messageText))
            }
        }

        binding.chatToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun show(messageModel: MutableList<MessageModel>) {
        adapter.push(messageModel)
    }

    override fun display(chat: ChatModel) {
        this.chatModel = chat
        adapter.push(chat.messages ?: mutableListOf())
    }
}