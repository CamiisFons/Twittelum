package com.example.twiitelum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twiitelum.application.TwittelumApplication
import com.example.twiitelum.bancodedados.TweetRepository
import com.example.twiitelum.db.TwittelumDatabase

object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val contexto = TwittelumApplication.getInstance()
        val database = TwittelumDatabase.getDatabase(contexto)
        val tweetDao = database.getTweetDao()
        val repository = TweetRepository(tweetDao)
        return TweetViewModel(repository) as T
    }
}