package com.example.justcodehm2

import android.content.Intent
import android.opengl.ETC1.isValid
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.justcodehm2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSayHelloButton()
        SetUpSendText()
    }
    private fun setUpSayHelloButton(){
        binding.sayHelloButton.setOnClickListener{
            if(isValid()){
                val intent= Intent(this,SecondActivity::class.java)
                intent.putExtra(ArgumentKey.NAME.name, binding.nameInputView.text.toString())
                startActivity(intent)

            }
            else {
                Toast.makeText(this,"You need input your count", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun SetUpSendText(){
        binding.sentBtn.setOnClickListener{
            if(isValid()){
                val intent=Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, binding.nameInputView.text.toString())
                intent.type="text|plan"
                val choseIntent= Intent.createChooser(intent,"Title")
                startActivity(choseIntent)
            }
            else{
                Toast.makeText(this,"You need write text",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValid()=!binding.nameInputView.text.isNullOrBlank()
}
enum class ArgumentKey{
    NAME
}