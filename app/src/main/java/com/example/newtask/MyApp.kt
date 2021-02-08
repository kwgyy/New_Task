package com.example.newtask

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.newtask.MainActivity
import java.io.File
import java.lang.Exception
import java.security.AccessController.getContext

class MyApp : Application() {

    var DIRECTORY: String? = null


    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private var instance : MyApp? = null
        fun  getInstance(): MyApp {
            if (instance == null)
                instance = MyApp()
            return instance!!
        }
    }



    /*フォルダを追加(name:フォルダ名)*/
    fun Add_File(name: String, text: String){
        val GLOBAL = MyApp.getInstance()
        if (GLOBAL.DIRECTORY == null) return
        try{
            File(GLOBAL.DIRECTORY + "/", name).writer().use {
                it.write(text)
            }
        }catch (e: Exception){

        }
    }

    /*フォルダを追加(name:フォルダ名)*/
    fun Add_Folder(name: String){
        val GLOBAL = MyApp.getInstance()
        if (GLOBAL.DIRECTORY == null) return
        try{
            val folder = File(GLOBAL.DIRECTORY + "/", name)
            folder.mkdir()
        }catch (e: Exception){

        }
    }

    /*探索用のリスト*/
    fun Add_List(name: String){

    }

    /*同じ名前のファイルを探索*/
    fun check_name(name:String):Boolean{
        val GLOBAL = MyApp.getInstance()
        if (GLOBAL.DIRECTORY == null) return false
        val check = File(GLOBAL.DIRECTORY + "/",name)
        return check.exists()
    }

    /*メッセージの表示*/
    fun toastMake(context: Context, message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }
}