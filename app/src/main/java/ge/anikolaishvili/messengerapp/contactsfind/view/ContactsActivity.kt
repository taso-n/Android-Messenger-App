package ge.anikolaishvili.messengerapp.contactsfind.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import ge.anikolaishvili.messengerapp.datamodel.ChatModel
import ge.anikolaishvili.messengerapp.datamodel.UserModel
import ge.anikolaishvili.messengerapp.contactsfind.ContactClickListener
import ge.anikolaishvili.messengerapp.contactsfind.ContactsAdapter
import ge.anikolaishvili.messengerapp.contactsfind.presenter.ContactsPresenter
import ge.anikolaishvili.messengerapp.databinding.AllContactsListBinding
import ge.anikolaishvili.messengerapp.textsetting.view.QuickTextActivity

class ContactsActivity : AppCompatActivity(), ContactClickListener, IContactsView {
    private lateinit var binding: AllContactsListBinding
    private lateinit var adapter: ContactsAdapter
    private val presenter: ContactsPresenter = ContactsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AllContactsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ContactsAdapter(this)
        adapter.pushFriends(mutableListOf())
        binding.myContacts.adapter = adapter
        binding.backButtonFriends.setOnClickListener {
            finish()
        }
        binding.searchBar.addTextChangedListener {
            presenter.getContacts(it.toString())
        }
        presenter.getContacts()
    }

    override fun didClickContact(at: Int) {
        presenter.addConv(ChatModel(interloc = adapter.userAt(at), messages = mutableListOf()))
    }

    override fun didAddChat(of: ChatModel) {
        startActivity(Intent(this, QuickTextActivity::class.java).putExtra("ge.anikolaishvili.messengerapp.putextra", of))
    }

    override fun displayContacts(friends: MutableList<UserModel>) {
        adapter.pushFriends(friends)
    }
}