package com.example.cadastro_de_usuario.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseSQLite(context: Context): SQLiteOpenHelper(context, db_NAME, null, db_VERSION) {

    companion object {
        private val db_VERSION = 1
        private val db_NAME = "ListUsers"
        private val TABLE_NAME = "User"
        private val ID = "Id"
        private val NAME = "Name"
        private val EMAIL = "email"
        private val PASSWORD = "Password"
        private val USER_TYPE = "Type"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, $NAME TEXT, $EMAIL TEXT, $PASSWORD TEXT, $USER_TYPE TEXT);"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME;"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAME, user.name)
            put(EMAIL, user.email)
            put(PASSWORD, user.password)
            put(USER_TYPE, user.type.toString())
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun getUser(id: Int): User {
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $id;"
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        val user = populateUser(cursor)
        cursor.close()
        return user
    }

    fun getListUser() : ArrayList<User> {
        val userList = ArrayList<User>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $NAME;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val user = populateUser(cursor)
                    userList.add(user)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return  userList
    }

    fun updateUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAME, user.name)
            put(EMAIL, user.email)
            put(PASSWORD, user.password)
        }
        db.update(TABLE_NAME, values, "$ID=?", arrayOf(user.id.toString()))
    }

    fun delUser(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$ID=?", arrayOf(id.toString()))
    }

    fun populateUser(cursor: Cursor): User {
        val user = User()
        with(user){
            id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
            name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
            email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
            password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD))
            type = cursor.getInt(cursor.getColumnIndexOrThrow(USER_TYPE))
        }

        return user
    }
}















