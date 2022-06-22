package com.ilaydabykakova.catchthericky

import android.content.Intent
import android.location.GnssAntennaInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.ilaydabykakova.catchthericky.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var score = 0
    var runnable : Runnable = Runnable{ }
    var handler : Handler = Handler()
    var imageArray = ArrayList<ImageView>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ImageArray
        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        // CountDown Timer
        object : CountDownTimer(15100,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Timer : ${millisUntilFinished/1000}"
            }

            //Alert Dialog
            override fun onFinish() {
                binding.timeText.text = "Timer Off !"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Are you sure to restart game ?")
                alert.setPositiveButton("Yes"){dialog,which ->
                    Toast.makeText(this@MainActivity,"Restarting...",Toast.LENGTH_SHORT).show()
                    //val intent = Intent(applicationContext,MainActivity::class.java)
                    //Restart
                    val intent = intent
                    finish() // Efficient to use(for the memory management), we close the app then restart again
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this@MainActivity,"Closing Game !",Toast.LENGTH_SHORT).show()
                    finish()
                }

                alert.show()
            }

        }.start()

        hideImages()
    }
    //Score
    fun increaseScore(view : View){
        score++
        binding.scoreText.text = "Score : ${score}"

    }
  fun hideImages(){

        runnable = object : Runnable{

            override fun run() {
                for (image in imageArray ){
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(this,500)

            }

        }
        handler.post(runnable)

        }

}