package com.coding.swissarmy

import androidx.lifecycle.LiveData
import com.coding.swissarmy.common.database.SwissArmyDAO
import com.coding.swissarmy.common.database.entities.MessageEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

class SwissArmyRepository(private val swissArmyDAO: SwissArmyDAO) {
    fun getAllMessages(): Flowable<List<MessageEntity>> = swissArmyDAO.getAllMessages()
    fun getFavoriteMessages(): LiveData<List<MessageEntity>> = swissArmyDAO.getFavoriteMessages()
    fun getMessagesByOwner(owner: String): Observable<List<MessageEntity>> = swissArmyDAO.getMessagesByOwner(owner)
    fun getMessagesById(id: Int): Flow<List<MessageEntity>> = swissArmyDAO.getMessagesById(id)
    fun insertMessage(message: MessageEntity) = swissArmyDAO.insertMessage(message)
    fun deleteMessage(message: MessageEntity) = swissArmyDAO.deleteMessage(message)
}