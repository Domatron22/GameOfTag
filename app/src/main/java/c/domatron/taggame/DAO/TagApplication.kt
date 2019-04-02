package c.domatron.taggame.DAO

import android.app.Application
import android.arch.persistence.room.Room

/* Author: Dominic Triano
 * Date: 3/31/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *  Instance of the application to use database
 *
 */

class TagApplication: Application() {

    companion object {
        var database: TagDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, TagDatabase::class.java,
            "Tag-db"
        ).build()
    }
}