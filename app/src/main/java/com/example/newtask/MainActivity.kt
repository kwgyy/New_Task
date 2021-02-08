package com.example.newtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    val GLOBAL = MyApp.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GLOBAL.DIRECTORY = "$filesDir" //ディレクトリ位置を指定

        //フラグメントを表示
        val fragment = ListFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame, fragment).commit()
    }

}