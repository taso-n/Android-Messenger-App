package ge.anikolaishvili.messengerapp.contactsfind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.anikolaishvili.messengerapp.databinding.SingleContactBinding
import ge.anikolaishvili.messengerapp.datamodel.UserModel

class ContactsAdapter(private val listener: ContactClickListener) : RecyclerView.Adapter<ContactsAdapter.FriendCellHolder>() {
    private lateinit var friends: MutableList<UserModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendCellHolder {
        return FriendCellHolder(SingleContactBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: FriendCellHolder, position: Int) {
        holder.configure(friends[position])
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    fun userAt(index: Int): UserModel {
        return friends[index]
    }

    fun getEmailAt(index: Int): String? {
        return friends[index].email
    }

    fun getProfessionAt(index: Int): String? {
        return friends[index].profession
    }

    fun pushFriends(friends: MutableList<UserModel>) {
        this.friends = friends
        notifyDataSetChanged()
    }


    class FriendCellHolder(private val binding: SingleContactBinding, private val listener: ContactClickListener) : RecyclerView.ViewHolder(binding.root) {

        fun configure(with: UserModel) {
            binding.friendName.text = with.email
            binding.friendProfession.text = with.profession
            addListeners()
        }

        private fun addListeners() {
            binding.root.setOnClickListener {
                listener.didClickContact(adapterPosition)
            }
        }
    }
}