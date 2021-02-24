package com.example.demoandroid.common

import android.content.Context
import android.net.ConnectivityManager

/**
 * Utility class
 */
class AppUtils {

    companion object {
        fun splitCharacters(name: String): List<String> = name.split("-")

        /**
         * Check the network connection and true if Wifi or MOBILE available
         */
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return !(activeNetwork != null && activeNetwork.isConnected)
        }
    }
}