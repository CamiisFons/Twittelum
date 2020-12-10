package com.example.twiitelum
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.db.TwittelumDatabase
import com.example.twiitelum.viewmodel.TweetViewModel
import com.example.twiitelum.viewmodel.ViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelFactory)[TweetViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_salvar -> {
            publicaTweet()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
    fun publicaTweet() {
        val campoConteudo = findViewById<EditText>(R.id.publicar_tweet)
        val conteudo = campoConteudo.text.toString()
        val tweet = Tweet(conteudo)
        val database = TwittelumDatabase.getDatabase(this)
        val tweetDao = database.getTweetDao()
        tweetDao.salva(tweet)

        Toast.makeText(this, conteudo, Toast.LENGTH_SHORT).show()

        finish()

    }
}