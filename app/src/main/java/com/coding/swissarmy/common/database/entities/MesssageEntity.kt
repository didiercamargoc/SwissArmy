package com.coding.swissarmy.common.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.Timestamp

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val message: String = "", val favorite: Boolean = false,
    val owner: String = "", val status: Boolean = false) {
}