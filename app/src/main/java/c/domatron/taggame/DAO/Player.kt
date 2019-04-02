package c.domatron.taggame.DAO

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Players")
data class Player(@ColumnInfo(name="user")   var user: String, //User Name
                  @ColumnInfo(name="tid")    var tid: String, //Tag ID, individual nfc tag
                  @ColumnInfo(name="pass")   var pass: String, //Password hashed
                  @ColumnInfo(name="status") var status: Int = 0) //status of player, 0 for not tagged, 1 for tagged