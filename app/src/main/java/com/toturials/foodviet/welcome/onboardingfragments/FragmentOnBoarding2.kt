package com.toturials.foodviet.welcome.onboardingfragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.toturials.foodviet.R
import com.toturials.foodviet.joinapp.Login
import kotlinx.android.synthetic.main.layout_onboarding_tab2.view.*

class FragmentOnBoarding2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_onboarding_tab2, container, false)
        view.skip_OB2.setOnClickListener {
            Intent(requireContext(), Login::class.java).also {
                startActivity(it)
                requireActivity().overridePendingTransition(
                    R.anim.anim_slide_left_to_right,
                    R.anim.anim_slide_right_to_left
                )
            }
        }
        return view
    }
}