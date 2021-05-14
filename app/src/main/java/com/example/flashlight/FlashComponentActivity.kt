package com.example.flashlight

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_flash_component.*
import java.lang.Exception
import java.util.jar.Manifest

class FlashComponentActivity : AppCompatActivity() {
val Reques_code= 8
    var hasFlash = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_component)
        settingUp()
button2.setOnClickListener {
            if (hasFlash){
                if (button.text.toString().contains("ON")){
                    myName.setText(" ")
                    button2.setImageResource(R.drawable.flashlight_off)
                    button.text=("Flashlight Off")
                    flashOff()
                }else{
                    myName.setText(" Mr Kelz")
                    button2.setImageResource(R.drawable.flashlight_on)
                    button.text=("Flashlight ON")
                    flashOn() }
            }else{
                Toast.makeText(this@FlashComponentActivity, " No FLash Light available", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun settingUp(){
        hasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        ActivityCompat.requestPermissions(this@FlashComponentActivity, arrayOf(android.Manifest.permission.CAMERA),Reques_code)

    }



    fun flashOn(){

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManager.setTorchMode(cameraId,true)
            }
        }catch (exp: Exception){ }
    }


    fun flashOff(){

        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = cameraManager.cameraIdList[0]
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                cameraManager.setTorchMode(cameraId,false)
            }
        }catch (exp: Exception){ }
    }

    //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            Reques_code -> {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    hasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
                }else{
                    button.isEnabled = false
                    Toast.makeText(this@FlashComponentActivity, "Permission Denied", Toast.LENGTH_LONG).show()

                }

            }
        }

    }



}
