package com.example.used_market.chatdetail

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.used_market.databinding.ItemChatBinding
import com.example.used_market.R
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class ChatItemAdapter : ListAdapter<ChatItem, ChatItemAdapter.ViewHolder>(diffUtil) {

    private val userColors = mutableMapOf<String, Int>()
    inner class ViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(chatItem: ChatItem) {

            val userColor = userColors.getOrPut(chatItem.senderId) {
                generateRandomColor()
            }

            if (!isCurrentUserEmail(chatItem.senderId)) {
                binding.senderTextView.setTextColor(userColor)
            }
            binding.senderTextView.text = chatItem.senderId
            binding.messageTextView.text = chatItem.message
        }

        private fun generateRandomColor(): Int {
            val randomColor = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            return randomColor
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