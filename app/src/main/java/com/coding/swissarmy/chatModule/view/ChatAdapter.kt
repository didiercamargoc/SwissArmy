package com.coding.swissarmy.chatModule.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.swissarmy.R
import com.coding.swissarmy.common.database.entities.MessageEntity
import com.coding.swissarmy.databinding.ItemMessageContactBinding
import com.coding.swissarmy.databinding.ItemMessageSelfBinding

class ChatAdapter(private val messages: List<MessageEntity>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val CONTACT = "contact"
        private const val SELF = "self"
        private val VIEW_TYPE_MESSAGE_SELF = R.layout.item_message_self
        private val VIEW_TYPE_MESSAGE_CONTACT = R.layout.item_message_contact
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].owner == SELF) {
            VIEW_TYPE_MESSAGE_SELF
        } else {
            VIEW_TYPE_MESSAGE_CONTACT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        if (viewType == VIEW_TYPE_MESSAGE_SELF) {
            return ViewHolderSelf(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_CONTACT) {
            return ViewHolderContact(view)
        } else throw IllegalArgumentException("Unknown viewType: $viewType")

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val owner = messages[position].owner
        val mesage = messages[position].message
        //val date = messages[position].date
        val favorite = messages[position].favorite
        val status = messages[position].status

        if (owner == SELF) {
            holder as ViewHolderSelf
            with(holder) {
                binding.tvMessageSelf.text = mesage
                //binding.tvDateSelf.text = date
                if (favorite) {
                    binding.ivFavoriteSelf.visibility = View.VISIBLE
                } else {
                    binding.ivFavoriteSelf.visibility = View.INVISIBLE
                }
                if (status) {
                    binding.ivStatusSelf.visibility = View.VISIBLE
                } else {
                    binding.ivStatusSelf.visibility = View.INVISIBLE
                }
            }
        } else if (owner == CONTACT) {
            holder as ViewHolderContact
            with(holder) {
                binding.tvMessageContact.text = mesage
                //binding.tvDateContact.text = date
                if (favorite) {
                    binding.ivFavoriteContact.visibility = View.VISIBLE
                } else {
                    binding.ivFavoriteContact.visibility = View.INVISIBLE
                }
                if (status) {
                    binding.ivStatusContact.visibility = View.VISIBLE
                } else {
                    binding.ivStatusContact.visibility = View.INVISIBLE
                }
            }
        }
    }

    inner class ViewHolderSelf(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemMessageSelfBinding.bind(itemView)
    }

    inner class ViewHolderContact(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding = ItemMessageContactBinding.bind(itemView)
    }

}