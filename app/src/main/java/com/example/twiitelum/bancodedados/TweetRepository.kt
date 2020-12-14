package com.example.twiitelum.bancodedados

import caelum.com.twittelumapp.bancodedados.TweetDao
import caelum.com.twittelumapp.modelo.Tweet

class TweetRepository
    (val fonteDeDados: TweetDao){
    fun lista() = fonteDeDados.lista()
    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)
    fun deleta(tweet: Tweet) = fonteDeDados.deleta(tweet)
}
