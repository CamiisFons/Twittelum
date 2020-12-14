package com.example.twiitelum.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.databinding.ItemTweetBinding

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {
    override fun getCount(): Int {
        return tweets.size
    }

    override fun getItem(position: Int): Any {
        return tweets[position]
    }

    override fun getItemId(position: Int): Long {
        return tweets[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemTweetBinding.inflate(inflater, parent, false)
        binding.itemTweetTexto.text = tweet.mensagem
        tweet.foto?.let {
            binding.itemTweetFoto.visibility = View.VISIBLE
            binding.itemTweetFoto.setImageBitmap(Carregador.decodifica(it))
        }
        return binding.root
    }

    object Carregador {

        fun decodifica(foto: String): Bitmap {
            val decode: ByteArray = Base64.decode(foto, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.size)
            return bitmap
        }
    }
}