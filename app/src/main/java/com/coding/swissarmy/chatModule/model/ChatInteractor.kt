package com.coding.swissarmy.chatModule.model

import android.util.Log
import com.coding.swissarmy.common.FirebaseManager
import com.coding.swissarmy.common.database.entities.MessageEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatInteractor {

    private val TAG = ChatInteractor::class.java.simpleName
    private val CHAT_COLLECTION = "Chat"
    val db = Firebase.firestore

    fun sendMessage(messageEntity: MessageEntity){
        db.collection(CHAT_COLLECTION)
            .add(messageEntity)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Message added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getMessages(onGetMessages: (List<MessageEntity>) -> Unit) {
        val ref = db.collection(CHAT_COLLECTION)
        ref.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val listMessages = mutableListOf<MessageEntity>()
                for (document in snapshot.documents) {
                    val messageEntity = document.toObject(MessageEntity::class.java)
                    listMessages.add(messageEntity!!)
                }
                onGetMessages(listMessages)
            }
        }
    }

}