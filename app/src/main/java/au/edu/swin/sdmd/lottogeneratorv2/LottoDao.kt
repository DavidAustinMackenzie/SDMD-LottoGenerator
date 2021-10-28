package au.edu.swin.sdmd.lottogeneratorv2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LottoDao {
    @Query("SELECT * FROM lotto_table ORDER BY id DESC")
    fun getGamesSortedById(): List<Lotto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(lotto:Lotto)

    @Query("DELETE FROM lotto_table")
    fun deleteAll()
}