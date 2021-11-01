package au.edu.swin.sdmd.lottogeneratorv2

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LottoDao {
    @Query("SELECT * FROM lotto_table ORDER BY id DESC")
    fun getGamesSortedById(): List<Lotto>

    @Query("SELECT * from lotto_table WHERE id=:id")
    fun getGame(id: Int): Lotto

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(lotto:Lotto)

    @Delete
    fun delete(lotto: Lotto)

    @Query("DELETE FROM lotto_table")
    fun deleteAll()

    @Update
    fun update(lotto: Lotto)
}