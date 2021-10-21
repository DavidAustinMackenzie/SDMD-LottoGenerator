package au.edu.swin.sdmd.lottogeneratorv2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lotto(val num1:Int, val num2: Int, val num3:Int, val num4:Int,
                 val num5:Int, val num6:Int, val num7:Int, val pow1:Int):Parcelable {
}