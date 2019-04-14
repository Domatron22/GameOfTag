package c.domatron.taggame.DAO

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/* Author: Dominic Triano
 * Date: 3/31/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *  Data access object for the player class for Rooms
 *
 */

@Dao
interface PlayerDao {

    @Query("SELECT * FROM Groups")
    fun getAll(): List<Player>

    @Query("SELECT user FROM Groups WHERE groupId = :gCode AND macAddr = :macAddr")
    fun getUser(gCode: String, macAddr: String): String


    @Insert
    fun insert(vararg Player: Player)

    @Query("DELETE FROM Groups WHERE tid = :tid")
    fun delete(vararg tid: String)
}