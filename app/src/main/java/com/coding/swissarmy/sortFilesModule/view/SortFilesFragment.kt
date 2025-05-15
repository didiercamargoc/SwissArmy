package com.coding.swissarmy.sortFilesModule.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coding.swissarmy.databinding.FragmentSortFilesBinding

class SortFilesFragment : Fragment() {

    private val TAG = SortFilesFragment::class.java.simpleName
    private lateinit var binding: FragmentSortFilesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortFilesBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun functionGitLogin(){
        Log.d(TAG, "Function Git Login")
    }

}