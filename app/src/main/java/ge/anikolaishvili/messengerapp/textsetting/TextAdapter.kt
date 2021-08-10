package ge.anikolaishvili.messengerapp.textsetting

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.anikolaishvili.messengerapp.R
import ge.anikolaishvili.messengerapp.datamodel.MessageModel
import ge.anikolaishvili.messengerapp.textsetting.view.ReceivedMessageCellViewHolder
import ge.anikolaishvili.messengerapp.textsetting.view.SentMessageCellViewHolder
import ge.anikolaishvili.messengerapp.textsetting.view.SingleMessageView

class TextAdapter() : RecyclerView.Adapter<SingleMessageView>() {
    private lateinit var messageModels: MutableList<MessageModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleMessageView {
        if (viewType == 0) {
            return SentMessageCellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_cell, parent, false))
        }
        return ReceivedMessageCellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.got_message_cell, parent, false))
    }

    override fun onBindViewHolder(holder: SingleMessageView, position: Int) {
        val msg = messageModels[position]
        holder.proccess(msg)
    }

    override fun getItemCount(): Int {
        Log.d("VIKA DEBUG", messageModels.size.toString())
        return messageModels.size
    }

    override fun getItemViewType(position: Int): Int {
        if (messageModels[position].gotText!!) {
            return 1
        }
        return 0
    }

    fun push(messageModels: MutableList<MessageModel>) {
        this.messageModels = messageModels
        notifyDataSetChanged()
    }
}
