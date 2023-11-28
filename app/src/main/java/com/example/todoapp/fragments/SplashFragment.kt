package com.example.todoapp.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSplashBinding
import java.lang.Thread.sleep

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
// Assuming you have a SplashFragment class that extends Fragment
class SplashFragment : Fragment() {

    // Declare a binding variable to hold the reference to the layout file
    private var _binding: FragmentSplashBinding? = null
    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    // Override the onCreateView method to inflate the layout file
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using the binding class
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        // Find the ImageView with id "logo" in the layout file using the binding object
        val imageView = binding.logo

        // Create an animation object that changes the alpha value from 0f to 1f in 1.5 seconds
        val imageAnimation = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        imageAnimation.duration = 1500 // 1500 milliseconds = 1.5 seconds

        // Create an animation object that changes the alpha value of the whole fragment from 1f to 0f in 1 second
        val fragmentAnimation = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        fragmentAnimation.duration = 600 // 1000 milliseconds = 1 second

        // Create an animation set that plays the image animation first and then the fragment animation
        val animationSet = AnimatorSet()
        animationSet.play(imageAnimation).before(fragmentAnimation)

        // Add a listener to the animation set that navigates to the main screen when the animation ends
        animationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // Assuming you have a NavController that controls the navigation between fragments
                val navController = findNavController()
                // Assuming you have a main screen fragment with id "main_fragment"
                navController.navigate(R.id.homeFragment)
            }
        })

        // Start the animation set when the fragment is created
        animationSet.start()

        // Return the view
        return view
    }

    // Override the onDestroyView method to clear the binding reference
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}