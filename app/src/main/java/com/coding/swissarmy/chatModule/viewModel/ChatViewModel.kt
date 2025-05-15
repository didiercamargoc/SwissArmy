package com.coding.swissarmy.chatModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.swissarmy.SwissArmyRepository
import com.coding.swissarmy.chatModule.model.ChatInteractor
import com.coding.swissarmy.common.database.entities.MessageEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: SwissArmyRepository) : ViewModel() {

    private val _messages = MutableLiveData<List<MessageEntity>>()
    val messages: LiveData<List<MessageEntity>> = _messages
    private val interactor = ChatInteractor()
    private val disposables = CompositeDisposable()

    fun getAllMessages() = repository.getAllMessages()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getFavoriteMessages() = repository.getFavoriteMessages()

    fun getMessagesByOwner(owner: String) = repository.getMessagesByOwner(owner)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insertMessage(messageEntity: MessageEntity) {
        interactor.sendMessage(messageEntity) // Firebase
        disposables.add(
            repository.insertMessage(messageEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun deleteMessage(messageEntity: MessageEntity) {
        disposables.add(
            repository.deleteMessage(messageEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    fun getMessages() {
        viewModelScope.launch {
            try {
                //val messages = interactor.getRemoteMessages()
                interactor.getMessages{ messages ->
                    _messages.value = messages // now you're in the main thread
                }

            } catch (e: Exception) {
                _messages.value = emptyList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}