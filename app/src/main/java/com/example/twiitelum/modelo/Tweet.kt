package caelum.com.twittelumapp.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tweet(val mensagem: String,
                 @PrimaryKey(autoGenerate = true) val id: Long = 0){
    override fun toString(): String {
        return mensagem
    }
}

