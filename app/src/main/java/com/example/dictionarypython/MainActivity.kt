package com.example.dictionarypython

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var hindiView: TextView
    private lateinit var wordView: TextView
    private lateinit var pyObj: PyObject
    private lateinit var py: Python

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.e2)
        wordView = findViewById(R.id.word_view)
        hindiView = findViewById(R.id.hindi_meaning)
        textView = findViewById(R.id.text_view)



            if (! Python.isStarted())
                Python.start( AndroidPlatform(this))

            py = Python.getInstance()
            pyObj = py.getModule("dictionary")


        editText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                try {

//                For hiding keyboard
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE)

                    val obj = pyObj.callAttr("main",editText.text.toString())


//                    set word view
                    wordView.text = editText.text.toString()

//                    clear edit text
                    editText.setText("")

//            set the result in text view
                    textView.text = obj.toString()


                }catch (e: Exception){
                    Toast.makeText(this, "Please Enter Right things", Toast.LENGTH_LONG).show()
                }
                true
            } else {
                false
            }
        }


    }

    }


