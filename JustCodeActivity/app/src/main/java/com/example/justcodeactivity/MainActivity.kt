package com.example.justcodeactivity

import android.app.Activity
import android.app.Notification.EXTRA_TEXT
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.opengl.ETC1.isValid
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.justcodeactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
 private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSayHelloButton()
        setUpTakePhotoButton()
        setUpOpenBrowser()
        SetUpSendText()
    }

     private fun setUpSayHelloButton(){
         binding.sayHelloButton.setOnClickListener{
             if(isValid()){
                 val intent= Intent(this,HelloActivity::class.java)
                 intent.putExtra(ArgumentKey.NAME.name, binding.nameInputView.text.toString())
                 startActivity(intent)

             }
             else {
                 Toast.makeText(this,"You need input your name",Toast.LENGTH_SHORT).show()
             }
         }
    }

    private fun setUpTakePhotoButton(){
        binding.takePhotoBtn.setOnClickListener{
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResult.launch(intent)

        }
    }

    private val cameraResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode==Activity.RESULT_OK){
                val photo=it.data?.extras?.get("data") as Bitmap?
                photo?.let{
                    binding.newImageView.setImageBitmap(it)
                }
            }

        }

    private fun setUpOpenBrowser(){
        binding.openBrowserBtn.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse("https://web.whatsapp.com/")
            )
            startActivity(intent)
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