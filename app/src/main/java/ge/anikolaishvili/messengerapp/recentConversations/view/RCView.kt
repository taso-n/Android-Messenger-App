package ge.anikolaishvili.messengerapp.recentConversations.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import ge.anikolaishvili.messengerapp.databinding.AllChatsBinding
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.recentConversations.RCAdapter
import ge.anikolaishvili.messengerapp.recentConversations.RCListener
import ge.anikolaishvili.messengerapp.recentConversations.presenter.IRCPresenter
import ge.anikolaishvili.messengerapp.recentConversations.presenter.RCPresenter
import ge.anikolaishvili.messengerapp.textsetting.view.QuickTextActivity

class RCView : Fragment(), RCListener, IRCView {

    private val presenter: IRCPresenter = RCPresenter(this)
    private var myFragment: AllChatsBinding? = null
    
    private val binding get() = myFragment!!
    
    private lateinit var adapter: RCAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myFragment = AllChatsBinding.inflate(inflater, container, false)
        val myView = binding.root

        presenter.getRecentConv()

        binding.allChat.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = RCAdapter(this)
        adapter.displayList(mutableListOf())
        binding.allChat.adapter = adapter

        binding.searchChatsField.addTextChangedListener {
            presenter.getRecentConv(it.toString())
        }
        return myView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        myFragment = null
    }

    override fun didChatClick(index: Int) {
        startActivity(Intent(context, QuickTextActivity::class.java).putExtra("ge.anikolaishvili.messengerapp.putextra", adapter.getIndex(index)))
    }

    override fun displayChats(recentConv: MutableList<ChatModel>) {
        adapter.displayList(recentConv)
    }
}