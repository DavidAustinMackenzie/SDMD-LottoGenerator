package au.edu.swin.sdmd.lottogeneratorv2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lotto_table")
class Lotto(@PrimaryKey (autoGenerate = true) val id:Int, @ColumnInfo(name="num1") var num1:Int,
            @ColumnInfo(name="num2") var num2: Int, @ColumnInfo(name="num3") var num3:Int,
            @ColumnInfo(name="num4") var num4:Int, @ColumnInfo(name="num5") var num5:Int,
            @ColumnInfo(name="num6") var num6:Int, @ColumnInfo(name="num7") var num7:Int,
            @ColumnInfo(name="pow1") var pow1:Int, @ColumnInfo(name="game") val game:Int = 1) {
}