package ge.anikolaishvili.messengerapp.recentConversations.view

import androidx.recyclerview.widget.RecyclerView
import ge.anikolaishvili.messengerapp.databinding.SingleChatItemBinding
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.recentConversations.RCListener
import java.text.SimpleDateFormat

class RCSingleItem(
    private val binding: SingleChatItemBinding,
    private val listener: RCListener
) : RecyclerView.ViewHolder(binding.root) {
    fun newItem(conversation: ChatModel) {

        val formatter = SimpleDateFormat("hh:mm")

        binding.rcEmail.text = conversation.interloc?.email
        binding.rcText.text = conversation.lastMessage?.content
        val formatted = formatter.format(conversation.lastMessage?.dateRec)
        binding.rcDate.text = formatted

        binding.root.setOnClickListener {
            listener.didChatClick(adapterPosition)
        }
    }

}