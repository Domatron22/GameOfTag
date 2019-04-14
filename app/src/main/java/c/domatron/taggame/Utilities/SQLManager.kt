package c.domatron.taggame.Utilities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import org.jetbrains.anko.db.*
import c.domatron.taggame.Utilities.Players

class SQLManager constructor(con: Context) : ManagedSQLiteOpenHelper(con, "TagDatabase", null, 1){


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
        db.createTable( "Groups", true,
                        "user" to TEXT,
                        "tid" to TEXT,
                        "status" to INTEGER,
                        "tCount" to INTEGER,
                        "groupId" to TEXT,
                        "macAddrs" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, OldVersion: Int, newVersion: Int)
    {
        db.dropTable("Groups", true)
    }

    fun addUser(player: Players): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("user", player.user)
        values.put("tid", player.tid)
        values.put("status", player.status)
        values.put("tCount", player.tCount)
        values.put("groupId", player.groupId)
        values.put("macAddrs", player.macAddrs)
        val _success = db.insert("Groups", null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    fun getUser() : String{
        var User: String = ""
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM Groups"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var user = cursor.getString(cursor.getColumnIndex("user"))
                    var tid = cursor.getString(cursor.getColumnIndex("tid"))
                    var status = cursor.getString(cursor.getColumnIndex("status"))
                    var tcount = cursor.getString(cursor.getColumnIndex("tcount"))
                    var groupId = cursor.getString(cursor.getColumnIndex("groupId"))
                    var macaddrs = cursor.getString(cursor.getColumnIndex("macAddrs"))

                    User = "$user"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return User

    }



}

// Access property for Context
val Context.database: SQLManager
    get() = SQLManager.getInstance(this)