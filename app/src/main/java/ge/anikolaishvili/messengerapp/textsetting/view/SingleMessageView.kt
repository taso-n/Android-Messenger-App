package ge.anikolaishvili.messengerapp.textsetting.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.anikolaishvili.messengerapp.R
import ge.anikolaishvili.messengerapp.datamodel.MessageModel

abstract class SingleMessageView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun configure(with: MessageModel)
}

class ReceivedMessageCellViewHolder(itemView: View) : SingleMessageView(itemView) {
    override fun configure(with: MessageModel) {
        val msgRec = itemView.findViewById<TextView>(R.id.messageRecCell)
        msgRec.text = with.content

        val msgTime = itemView.findViewById<TextView>(R.id.messageRecTimeCell)
        msgTime.text = with.getFormattedTime()
    }
}

class SentMessageCellViewHolder(itemView: View) : SingleMessageView(itemView) {
    override fun configure(with: MessageModel) {
        val msgSent = itemView.findViewById<TextView>(R.id.messageSentCell)
        msgSent.text = with.content

        val msgTime = itemView.findViewById<TextView>(R.id.messageSentTimeCell)
        msgTime.text = with.getFormattedTime()
    }
}