package com.coding.swissarmy.common.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.coding.swissarmy.common.database.entities.MessageEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

@Dao
interface SwissArmyDAO {

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flowable<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE favorite = 1")
    fun getFavoriteMessages(): LiveData<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE owner = :owner")
    fun getMessagesByOwner(owner: String): Observable<List<MessageEntity>>

    @Query("SELECT * FROM messages WHERE id = :id")
    fun getMessagesById(id: Int): Flow<List<MessageEntity>>

    @Insert
    fun insertMessage(message: MessageEntity): Completable

    @Delete
    fun deleteMessage(message: MessageEntity): Completable

}