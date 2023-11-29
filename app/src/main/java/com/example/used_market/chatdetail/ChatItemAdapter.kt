package com.example.used_market.chatdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.used_market.databinding.ItemChatBinding
import com.example.used_market.R
import com.google.firebase.auth.FirebaseAuth

class ChatItemAdapter : ListAdapter<ChatItem, ChatItemAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chatItem: ChatItem) {
//            binding.senderTextView.text = chatItem.senderId
//            binding.messageTextView.text = chatItem.message

            if (isCurrentUserEmail(chatItem.senderId)) {
                binding.senderTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.blue))
            } else {
                binding.senderTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.yellow))
            }
            binding.senderTextView.text = chatItem.senderId
            binding.messageTextView.text = chatItem.message
        }
        private fun isCurrentUserEmail(senderId: String): Boolean {
            return senderId == FirebaseAuth.getInstance().currentUser?.email
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatItem>() {
            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                return oldItem == newItem
            }


        }
    }

}