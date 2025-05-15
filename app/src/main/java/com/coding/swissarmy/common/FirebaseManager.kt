package com.coding.swissarmy.common

import android.util.Log
import com.coding.swissarmy.common.database.entities.MessageEntity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirebaseManager {

    private val TAG = FirebaseManager::class.java.simpleName
    private val CHAT_COLLECTION = "Chat"
    val db = Firebase.firestore


}