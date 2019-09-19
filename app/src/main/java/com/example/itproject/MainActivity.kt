package com.example.itproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.frame_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainButton : CircleImageView
    private lateinit var fragmentTransaction : FragmentTransaction
    private var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainButton = findViewById(R.id.circleImgview_main)

        mainButton.setOnClickListener {

            fragmentTransaction =supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.framelayout_main, MainFragment()).commit()


        }
    }

    override fun onBackPressed() {

        val fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.framelayout_main)

        if(fragment != null) {

            if(count == 1) {
                val mainFragment : MainFragment = supportFragmentManager.findFragmentById(R.id.framelayout_main) as MainFragment
                mainFragment.back()
            }

            if(count == 2) {
                val pictureFragment : PictureFragment = supportFragmentManager.findFragmentById(R.id.framelayout_main) as PictureFragment
                pictureFragment.back()
                count = 1
            }

        }

        else {
            super.onBackPressed()
        }

    }

    fun setCount(i : Int) {
        count = i
    }

    fun getCount() : Int {
        return count
    }

}
