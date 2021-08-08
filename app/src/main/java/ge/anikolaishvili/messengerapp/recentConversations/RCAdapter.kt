package ge.anikolaishvili.messengerapp.recentConversations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.anikolaishvili.messengerapp.databinding.SingleChatItemBinding
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.recentConversations.view.RCSingleItem

class RCAdapter(private val listener: RCListener) :
    RecyclerView.Adapter<RCSingleItem>() {
    private var list: MutableList<ChatModel> = mutableListOf()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RCSingleItem {
        val binding =
            SingleChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RCSingleItem(binding, listener)
    }

    override fun onBindViewHolder(holder: RCSingleItem, position: Int) {
        holder.newItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun getIndex(index: Int): ChatModel {
        return  list.get(index)
    }

    fun displayList(recentConv: MutableList<ChatModel>) {
        this.list = recentConv
        notifyDataSetChanged()
    }

}

