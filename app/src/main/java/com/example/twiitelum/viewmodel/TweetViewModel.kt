package com.example.twiitelum.viewmodel

import androidx.lifecycle.ViewModel
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.bancodedados.TweetRepository

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {
    fun lista() = repository.lista()
    fun salva(tweet: Tweet) = repository.salva(tweet)
    fun deleta(tweet: Tweet) = repository.deleta(tweet)
}