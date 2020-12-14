package caelum.com.twittelumapp.bancodedados

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import caelum.com.twittelumapp.modelo.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)

    @Query("select * from Tweet")
    fun lista(): LiveData<List<Tweet>>

    @Delete
    fun deleta(tweet: Tweet)
}