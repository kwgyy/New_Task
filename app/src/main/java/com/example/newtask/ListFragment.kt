package com.example.newtask

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.EditText as EditText


class ListFragment : Fragment() {
    val GLOBAL = MyApp.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btn_flg = false
        val add_btn = view.findViewById<FloatingActionButton>(R.id.add_btn)
        val task_btn = view.findViewById<FloatingActionButton>(R.id.task_btn)
        val directory_btn = view.findViewById<FloatingActionButton>(R.id.directory_btn)
        add_btn.setOnClickListener{
            if(!btn_flg){
                directory_btn.setVisibility(View.VISIBLE)
                task_btn.setVisibility(View.VISIBLE)
                btn_flg = true
            }else{
                directory_btn.setVisibility(View.INVISIBLE)
                task_btn.setVisibility(View.INVISIBLE)
                btn_flg = false
            }
        }

        task_btn.setOnClickListener {
            add_dialog("タスク名を入力",1)
        }

        directory_btn.setOnClickListener {
            add_dialog("ディレクトリ名を入力",2  )
        }
    }

    /*データ追加用ダイアログ(title:タイトル,type=(1:タスク,2:ディレクトリ))*/
    fun add_dialog(title:String,type:Int){
        val myedit = EditText(requireContext())
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(title)
        dialog.setView(myedit)
        dialog.setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            // OKボタン押したときの処理
            val work = myedit.getText().toString()
            if(type == 1){
                //タスクとして追加する
                Add(work,1)

            }else if(type == 2){
                //ディレクトリとして追加する
                Add(work,2)
            }
            //完了メッセージの表示
        })
        dialog.setNegativeButton("キャンセル",null)
        dialog.show()
    }

    fun Add(name:String,type:Int){
        if(!GLOBAL.check_name(name)){
            when(type){
                1-> //タスクを追加する場合
                {
                    GLOBAL.Add_File(name,"")
                }

                2-> //ディレクトリを作成する場合
                {
                    GLOBAL.Add_Folder(name)
                }
            }
            GLOBAL.Add_List(name)
        }else{
            GLOBAL.toastMake(requireContext(),"すでに存在します")
        }
    }



}