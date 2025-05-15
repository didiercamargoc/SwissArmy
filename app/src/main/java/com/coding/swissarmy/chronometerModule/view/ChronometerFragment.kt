package com.coding.swissarmy.chronometerModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coding.swissarmy.databinding.FragmentChronometerBinding

class ChronometerFragment : Fragment() {

    private lateinit var binding: FragmentChronometerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChronometerBinding.inflate(inflater, container, false)

        return binding.root
    }

}