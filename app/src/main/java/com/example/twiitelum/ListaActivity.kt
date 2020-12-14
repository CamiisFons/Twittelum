package com.example.twiitelum

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.bancodedados.TweetDao
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.adapter.TweetAdapter
import com.example.twiitelum.databinding.ActivityListaBinding
import com.example.twiitelum.db.TwittelumDatabase
import com.example.twiitelum.viewmodel.TweetViewModel
import com.example.twiitelum.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ListaActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy{
        ViewModelProvider(this,ViewModelFactory).get(TweetViewModel::class.java)
    }
    lateinit var  binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listaTweet.setOnItemClickListener{adapter, view, position, id ->

            val builder = AlertDialog.Builder(this)
            builder.setIcon(R.drawable.ic_warning)
            builder.setTitle("Deseja deletar?")
            builder.setMessage("Tem certeza?")
            builder.setPositiveButton("Sim"){_,_ ->
                val tweet = adapter.getItemAtPosition(position) as Tweet
                viewModel.deleta(tweet)
            }

            builder.setNegativeButton("Não"){_,_ ->}
            builder.setNeutralButton("Não sei"){_,_ ->}
            builder.show()
        }
        viewModel.lista().observe(this,observer())
        binding.fabAdd.setOnClickListener{
            val intencao = Intent(this, MainActivity::class.java)
            startActivity(intencao)
        }
    }
    private  fun observer():Observer<List<Tweet>>{
        return Observer {
            tweets ->
            tweets?.let{
                binding.listaTweet.adapter = TweetAdapter(tweets)
            }
        }
    }
}