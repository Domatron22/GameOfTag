package c.domatron.taggame.Utilities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SQLManager private constructor(con: Context) : ManagedSQLiteOpenHelper(con, "TagDatabase", null, 1){
    init{
        instance = this
    }

    companion object {
        private var instance: SQLManager? = null

        //TODO -- NULL PROBLEM IS HERE ^^ and it is still null when it gets used below.
        @Synchronized
        fun getInstance(ctx: Context) = instance ?: SQLManager(ctx.applicationContext)
    }

    override fun onCreate(db: SQLiteDatabase)
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

    override fun onUpgrade(db: SQLiteDatabase, OldVersion: Int, newVersion: Int)
    {
        db.dropTable("Group", true)
    }



}

// Access property for Context
val Context.database: SQLManager
    get() = SQLManager.getInstance(this)