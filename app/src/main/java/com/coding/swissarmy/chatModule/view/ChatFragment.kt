package com.coding.swissarmy.chatModule.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.swissarmy.SwissArmyRepository
import com.coding.swissarmy.chatModule.viewModel.ChatViewModel
import com.coding.swissarmy.common.database.SwissArmyDatabase
import com.coding.swissarmy.common.database.entities.MessageEntity
import com.coding.swissarmy.databinding.FragmentChatBinding
import java.time.LocalDate

class ChatFragment : Fragment() {

    private val TAG = ChatFragment::class.java.simpleName
    private lateinit var binding: FragmentChatBinding
    private lateinit var viewModel: ChatViewModel
    private lateinit var adapter: ChatAdapter

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        val swissDAO = SwissArmyDatabase.getDatabase(requireContext()).swissArmyDAO()
        val repository = SwissArmyRepository(swissDAO)
        viewModel = ChatViewModel(repository)

        binding.rvChat.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewModel.getAllMessages() // Room
            .subscribe { messages ->
                adapter = ChatAdapter(messages)
                binding.rvChat.adapter = adapter
            }

        binding.fabSendMessage.setOnClickListener {
            val message = binding.etWriteMessage.text.toString()
            if (message.isNotBlank()) {
                viewModel.insertMessage(getMessageEntity(message))
            }

        }

        viewModel.getMessages() // Firebase
        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            messages.forEach { message ->
                Log.d(TAG, "Message: ${message.message}")
                adapter = ChatAdapter(messages)
                binding.rvChat.adapter = adapter
            }
        }

        return binding.root
    }

    private fun getMessageEntity(message: String): MessageEntity {
        val today: LocalDate = LocalDate.now()
        return MessageEntity(
            message = message,
            owner = "self",
            //date = today.toString(),
            favorite = false,
            status = false
        )
    }


}