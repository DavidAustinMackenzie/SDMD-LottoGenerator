package au.edu.swin.sdmd.lottogeneratorv2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import au.edu.swin.sdmd.lottogeneratorv2.models.Lotto

//Annotates class to be a Room Database with a table (entity) of the Lotto class
@Database(entities = arrayOf(Lotto::class),version = 1, exportSchema = false)
abstract class LottoRoomDatabase: RoomDatabase() {
    abstract fun lottoDao(): LottoDao

    companion object{
        @Volatile private var instance: LottoRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context:Context) = Room.databaseBuilder(context,
            LottoRoomDatabase::class.java, "lotto-list.db")
            .allowMainThreadQueries()
            .build()
    }
}