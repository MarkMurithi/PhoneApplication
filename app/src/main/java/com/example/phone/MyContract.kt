package com.example.phone

import android.net.Uri

object MyContentProviderContract {
    const val CONTENT_AUTHORITY = "com.contentprovider"
    const val PATH_PHONE_NUMBERS = "phone_numbers"
    const val PHONE_NUMBER = "phone_number"

    val CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY/$PATH_PHONE_NUMBERS")
}