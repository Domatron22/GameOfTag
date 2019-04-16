package c.domatron.taggame.Utilities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AlertDialog
import android.util.Log
import org.jetbrains.anko.db.*
import c.domatron.taggame.Utilities.Players
import java.io.File
import java.util.*
import org.jetbrains.anko.toast
import kotlin.coroutines.coroutineContext


class SQLManager constructor(con: Context) : SQLiteOpenHelper(con, "TagDatabase", null, 1){


    private var mDataBase: SQLiteDatabase? = null

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
        val CREATE_TABLE = "CREATE TABLE Groups " +
                "(user TEXT, tid TEXT, status INTEGER, tcount INTEGER, groupId TEXT, macAddrs TEXT)"
        db?.execSQL(CREATE_TABLE)

//        db.createTable( "Groups", true,
//                        "user" to TEXT,
//                        "tid" to TEXT,
//                        "status" to INTEGER,
//                        "tcount" to INTEGER,
//                        "groupId" to TEXT,
//                        "macAddrs" to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, OldVersion: Int, newVersion: Int)
    {
        db.dropTable("Groups", true)
    }

    fun addUser(player: Players): Boolean {
        //boot the database
        val db = this.writableDatabase

        //Get the values of the Player
        val values = ContentValues()
        values.put("user", player.user)
        values.put("tid", player.tid)
        values.put("status", player.status)
        //values.put("tcount", player.tcount)
        values.put("groupId", player.groupId)
        values.put("macAddrs", player.macAddrs)

        //Insert the values into the table
        val success = db.insert("Groups", null, values)

        //close the database
        db.close()
        return (Integer.parseInt("$success") != -1)
    }

    fun getUser() : String{
        var User = ""
        //boot the database
        val db = readableDatabase

        val selectALLQuery = "SELECT * FROM Groups"
        val cursor = db.rawQuery(selectALLQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var user = cursor.getString(cursor.getColumnIndex("user"))
                    //var tid = cursor.getString(cursor.getColumnIndex("tid"))
                    //var status = cursor.getString(cursor.getColumnIndex("status"))
                    //var tcount = cursor.getString(cursor.getColumnIndex("tcount"))
                    //var groupId = cursor.getString(cursor.getColumnIndex("groupId"))
                    //var macaddrs = cursor.getString(cursor.getColumnIndex("macAddrs"))

                    User = "$user"
                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return User

    }

    fun getStatus(): String
    {
        var Status = ""
        //boot the database
        val db = readableDatabase

        val selectALLQuery = "SELECT * FROM Groups"
        val cursor = db.rawQuery(selectALLQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //var user = cursor.getString(cursor.getColumnIndex("user"))
                    //var tid = cursor.getString(cursor.getColumnIndex("tid"))
                    var status = cursor.getString(cursor.getColumnIndex("status"))
                    //var tcount = cursor.getString(cursor.getColumnIndex("tcount"))
                    //var groupId = cursor.getString(cursor.getColumnIndex("groupId"))
                    //var macaddrs = cursor.getString(cursor.getColumnIndex("macAddrs"))

                    Status = "$status"
                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return Status
    }

    fun setTag(tag: String, User: String)
    {
        //boot the database
        val db = writableDatabase

        //Add the user's tag to the database
        db.rawQuery("UPDATE Groups SET tid = :tag WHERE user = :User", null)

        db.close()
    }

    fun getTag(): String
    {
        var Tag = ""
        //boot the database
        val db = readableDatabase

        val selectALLQuery = "SELECT * FROM Groups"
        val cursor = db.rawQuery(selectALLQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //var user = cursor.getString(cursor.getColumnIndex("user"))
                    var tid = cursor.getString(cursor.getColumnIndex("tid"))
                    //var status = cursor.getString(cursor.getColumnIndex("status"))
                    //var tcount = cursor.getString(cursor.getColumnIndex("tcount"))
                    //var groupId = cursor.getString(cursor.getColumnIndex("groupId"))
                    //var macaddrs = cursor.getString(cursor.getColumnIndex("macAddrs"))

                    Tag = "$tid"
                } while (cursor.moveToNext())
            }
        }

        cursor.close()
        db.close()
        return Tag
    }

    fun setStatus()
    {
        //TODO -- set the status of th individual who has been tagged

//        val url = javaClass.getResource("http://web.cs.sunyit.edu/~trianod/Player.db")
//        val f = File(url!!.toURI())
    }

    fun fetchDatabase(): Int
    {
        try {
            mDataBase = SQLiteDatabase.openDatabase(
                "http://web.cs.sunyit.edu/~trianod/Player.db",
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY
            )
        }catch(e: SQLiteCantOpenDatabaseException)
        {

        }


        if(mDataBase != null) {
            var User = ""
            //boot the database

            val selectALLQuery = "SELECT * FROM Groups"
            val cursor = mDataBase!!.rawQuery(selectALLQuery, null)

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        var user = cursor.getString(cursor.getColumnIndex("user"))
                        //var tid = cursor.getString(cursor.getColumnIndex("tid"))
                        //var status = cursor.getString(cursor.getColumnIndex("status"))
                        //var tcount = cursor.getString(cursor.getColumnIndex("tcount"))
                        //var groupId = cursor.getString(cursor.getColumnIndex("groupId"))
                        //var macaddrs = cursor.getString(cursor.getColumnIndex("macAddrs"))

                        User = "$user"
                    } while (cursor.moveToNext())
                }
            }

            cursor.close()
            mDataBase!!.close()

            println(User)
            return 1
        }else{
            return -1
        }
    }

}

// Access property for Context
val Context.database: SQLManager
    get() = SQLManager.getInstance(this)