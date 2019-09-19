package com.example.itproject

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import de.hdodenhof.circleimageview.CircleImageView

class MainFragment : Fragment() {

    private lateinit var mainButton : CircleImageView
    private lateinit var cameraButton : ImageView
    private lateinit var pencilButton : ImageView
    private lateinit var background_view : View
    private lateinit var disappearance_left : Animation
    private lateinit var disappearance_right : Animation
    private lateinit var anim_reduction : Animation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.frame_main, container, false)

        mainButton = view.findViewById(R.id.main_button_sub)

        val anim_expansion : Animation = AnimationUtils.loadAnimation(activity, R.anim.expansion)
        val appearance_left : Animation = AnimationUtils.loadAnimation(activity, R.anim.appearance_left)
        val appearance_right : Animation = AnimationUtils.loadAnimation(activity, R.anim.appearance_right)

        anim_reduction  = AnimationUtils.loadAnimation(activity, R.anim.reduction)
        disappearance_left  = AnimationUtils.loadAnimation(activity, R.anim.disappearance_left)
        disappearance_right  = AnimationUtils.loadAnimation(activity, R.anim.disappearance_right)

        cameraButton = view.findViewById(R.id.imageview_camera)
        pencilButton = view.findViewById(R.id.imageview_pencil)

        background_view = view.findViewById(R.id.view_frame)

        background_view.alpha = 1f

        if(MainActivity().getCount() == 0) {
            cameraButton.animation = appearance_right
            pencilButton.animation = appearance_left
            mainButton.animation = anim_expansion
        }

        mainButton.setOnClickListener {

            val fragmentManager : FragmentManager= activity!!.supportFragmentManager

            mainButton.clearAnimation()
            cameraButton.clearAnimation()
            pencilButton.clearAnimation()

            background_view.alpha = 0f

            mainButton.animation = anim_reduction
            cameraButton.animation = disappearance_left
            pencilButton.animation = disappearance_right

            Handler().postDelayed({
                fragmentManager.beginTransaction().remove(MainFragment@this).commit()
            }, anim_reduction.duration)

        }

        background_view.setOnClickListener {

            val fragmentManager : FragmentManager= activity!!.supportFragmentManager

            mainButton.clearAnimation()
            cameraButton.clearAnimation()
            pencilButton.clearAnimation()

            background_view.alpha = 0f
            mainButton.animation = anim_reduction
            cameraButton.animation = disappearance_left
            pencilButton.animation = disappearance_right

            Handler().postDelayed({
                fragmentManager.beginTransaction().remove(MainFragment@this).commit()
            }, anim_reduction.duration)

            MainActivity().setCount(0)

        }

        cameraButton.setOnClickListener {
            fragmentManager!!.beginTransaction().remove(MainFragment@this).commit()
            fragmentManager!!.beginTransaction().add(R.id.framelayout_main, PictureFragment()).commit()
        }

        return view

    }

    fun back() {

        val fragmentManager : FragmentManager= activity!!.supportFragmentManager

        mainButton.clearAnimation()
        cameraButton.clearAnimation()
        pencilButton.clearAnimation()

        background_view.alpha = 0f
        mainButton.animation = anim_reduction
        cameraButton.animation = disappearance_left
        pencilButton.animation = disappearance_right

        Handler().postDelayed({
            fragmentManager.beginTransaction().remove(MainFragment@this).commit()
        }, anim_reduction.duration)

    }

}