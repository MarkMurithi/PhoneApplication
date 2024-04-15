package com.example.phone

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PhoneNumberDatabase(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        const val DATABASE_NAME = "phone_numbers.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "phone_numbers"
        const val COLUMN_ID = "_id"
        const val COLUMN_PHONE_NUMBER = "phone_number"

        private val CREATE_TABLE_QUERY = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_PHONE_NUMBER TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPhoneNumber(phoneNumber: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_PHONE_NUMBER, phoneNumber)
        db.insert(TABLE_NAME, null, contentValues)
        db.close()
    }

    @SuppressLint("Range")
    fun getPhoneNumbers(): MutableList<String> {
        val list = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_PHONE_NUMBER), null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE_NUMBER)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun query(
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    fun update(values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Not yet implemented")
    }

    fun delete(selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Not yet implemented")
    }

    fun insert(values: ContentValues?): Any {
        TODO("Not yet implemented")
    }

}