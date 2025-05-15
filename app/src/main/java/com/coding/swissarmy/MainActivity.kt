package com.coding.swissarmy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.coding.swissarmy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val TAG = MainActivity::class.java.simpleName
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    private fun functionGit(){
        Log.d(TAG, "Function Git")
    }

    private fun anotherFunctionGit(){
        Log.d(TAG, "Another function Git with changes")
    }

    private fun anooooootherFuncionGit(){
        Log.d(TAG, "Another function Git with changes")
    }

}