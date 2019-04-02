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

    @Query("SELECT * FROM Players")
    fun getAll(): List<Player>

    @Query("SELECT pass FROM Players WHERE user = :user")
    fun getPass(vararg user: String): String

    @Insert
    fun insert(vararg listCategories: Player)

    @Query("DELETE FROM Players WHERE tid = :tid")
    fun delete(vararg tid: String)
}