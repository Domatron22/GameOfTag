package c.domatron.taggame.Utilities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

abstract class SQLManager (con: Context) : ManagedSQLiteOpenHelper(con, "TagDatabase", null, 1){
    companion object {
        private var instance: SQLManager? = null

        @Synchronized
        fun getInstance(context: Context): SQLManager {
            return instance!!
        }
    }

    fun create(db: SQLiteDatabase)
    {
        //Create Tables
        //Creating a table with all of the same variables as the data class
        db.createTable( "Group", true,
                        "user" to TEXT,
                        "tid" to TEXT,
                        "status" to INTEGER,
                        "tCount" to INTEGER,
                        "groupId" to TEXT,
                        "macAddrs" to TEXT)
    }

    fun update(db: SQLiteDatabase, id: Int, field: String, newVal: String)
    {
        /*TODO -- Takes out old value in the field provided for the user provided and puts in new one */
    }

}

// Access property for Context
val Context.database: SQLManager
    get() = SQLManager.getInstance(this)