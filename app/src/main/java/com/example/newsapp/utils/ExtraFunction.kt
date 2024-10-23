package com.example.newsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter

object ExtraFunction {

    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun writeLastQueryToFile(lastQuery: String, context: Context) {
//        try {
//            val file = File("cachedQuery.txt")
//            val writer = FileWriter(file)
//            writer.write(lastQuery)
//            writer.close()
//        } catch (e:  Exception){
//            Log.d("WRITING", e.message.toString())
//        }
        try {
            val outputStreamWriter =
                OutputStreamWriter(
                    context.openFileOutput(
                        "cachedQuery.txt",
                        Context.MODE_PRIVATE
                    )
                )
            outputStreamWriter.write(lastQuery)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("WRITING", "File write failed :", e)
        }
    }

    fun readLastQueryFromFile(context: Context): String {
        return try {
            val inputStream = context.openFileInput("cachedQuery.txt")

            var receiveString = ""
            if (inputStream != null) {
                receiveString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
            }

            receiveString
        } catch (e: FileNotFoundException) {
            Log.e("READING", "File not found :", e)
            ""
        } catch (e: IOException) {
            Log.e("READING", "Can not read file :", e)
            ""
        }
    }
}