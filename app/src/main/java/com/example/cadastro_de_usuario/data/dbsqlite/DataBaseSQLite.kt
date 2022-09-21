package com.example.cadastro_de_usuario.data.dbsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cadastro_de_usuario.domain.vo.UserDataVO

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

    fun addUser(userDataVO: UserDataVO) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAME, userDataVO.name)
            put(EMAIL, userDataVO.email)
            put(PASSWORD, userDataVO.password)
            put(USER_TYPE, userDataVO.type.toString())
        }
        db.insert(TABLE_NAME, null, values)
    }

    fun getUserList() : ArrayList<UserDataVO> {
        val userDataVOList = ArrayList<UserDataVO>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $NAME;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val user = populateUser(cursor)
                    userDataVOList.add(user)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return  userDataVOList
    }

    fun updateUser(userDataVO: UserDataVO) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(NAME, userDataVO.name)
            put(EMAIL, userDataVO.email)
            put(PASSWORD, userDataVO.password)
        }
        db.update(TABLE_NAME, values, "$ID=?", arrayOf(userDataVO.id.toString()))
    }

    fun delUser(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$ID=?", arrayOf(id.toString()))
    }

    fun populateUser(cursor: Cursor): UserDataVO {
        val userDataVO = UserDataVO()
        with(userDataVO){
            id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
            name = cursor.getString(cursor.getColumnIndexOrThrow(NAME))
            email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL))
            password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD))
            type = cursor.getInt(cursor.getColumnIndexOrThrow(USER_TYPE))
        }

        return userDataVO
    }
}















