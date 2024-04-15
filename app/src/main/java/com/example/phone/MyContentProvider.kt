package com.example.phone

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.text.util.Linkify.PHONE_NUMBERS


class MyContentProvider : ContentProvider() {

    private lateinit var database: PhoneNumberDatabase


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val match = sUriMatcher.match(uri)
        return when (match) {
            PHONE_NUMBERS -> database.delete(selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        val match = sUriMatcher.match(uri)
        return when (match) {
            PHONE_NUMBERS -> "vnd.android.cursor.dir/vnd.${MyContentProviderContract.CONTENT_AUTHORITY}.$PATH_PHONE_NUMBERS"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val newRowId = database.insert(values)
        return Uri.withAppendedPath(MyContentProviderContract.CONTENT_URI, newRowId.toString())
    }

    override fun onCreate(): Boolean {
        database = PhoneNumberDatabase(context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val match = sUriMatcher.match(uri)
        return when (match) {
            PHONE_NUMBERS -> database.query(
                projection,
                selection,
                selectionArgs,
                sortOrder
            )
            else -> throw IllegalArgumentException("Unkown URI: $uri")
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val match = sUriMatcher.match(uri)
        return when (match) {
            PHONE_NUMBERS -> database.update(values,selection, selectionArgs)
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
    companion object {
        private const val PATH_PHONE_NUMBERS = 100


        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(MyContentProviderContract.CONTENT_AUTHORITY, PATH_PHONE_NUMBERS, PHONE_NUMBERS)

        }

        private fun addURI(contentAuthority: String, pathPhoneNumbers: Int, phoneNumbers: Int) {

        }
    }
}