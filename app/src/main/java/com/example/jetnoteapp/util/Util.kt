package com.example.jetnoteapp.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timeStamp: Long): String{

    // first converting long to date
    val date = Date(timeStamp)
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa",
        Locale.getDefault()
    )

    return format.format(date)
}