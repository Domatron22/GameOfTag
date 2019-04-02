package c.domatron.taggame.DAO

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.DAO.PlayerDao

/* Author: Dominic Triano
 * Date: 3/31/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *  App database for the room
 *
 */

@Database(entities = [(Player::class)], version = 1)
abstract class TagDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}