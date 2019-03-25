package c.domatron.taggame

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.speech.tts.TextToSpeech
import org.jetbrains.anko.db.*

class SQLManager (con: Context) : ManagedSQLiteOpenHelper(ctx, "TagDatabase", null, 1){
    companion object {
        private var instance: SQLManager? = null

        @Synchronized
        fun Instance(context: Context): SQLManager {
            if (instance == null) {
                instance = SQLManager(context.applicationContext)
            }
            return instance!!
        }
    }

    fun create(db: SQLiteDatabase)
    {
        //Create Tables
        //Creating a table with all of the same variables as the data class
        db.createTable( "Player", true,
                        "pid" to INTEGER + PRIMARY_KEY + UNIQUE + AUTOINCREMENT,
                        "user" to TEXT,
                        "tid" to TEXT,
                        "pass" to TEXT
                        "status" to INTEGER)
    }

    fun update(db: SQLiteDatabase, id: Int, field: String, newVal: String)
    {
        //Takes out old value in the field provided for the user provided and puts in new one
    }

}