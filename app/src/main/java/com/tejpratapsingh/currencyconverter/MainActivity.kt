package com.tejpratapsingh.currencyconverter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tejpratapsingh.currencyconverter.databinding.ActivityMainBinding
import com.tejpratapsingh.currencyconverter.interfaces.NetworkStatus
import com.tejpratapsingh.currencyconverter.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var shortAnimationDuration: Int = 2000

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_converter, R.id.navigation_history
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        listenToNetwork()
    }

    private fun listenToNetwork() = lifecycleScope.launch {
        mainViewModel.networkStatus.collect {
            when (it) {
                NetworkStatus.Connected -> {
                    binding.textMainError.text = getString(R.string.connected)
                    binding.textMainError.setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity, R.color.text_success
                        )
                    )
                    binding.cardMainError.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity, R.color.success_bg
                        )
                    )
                    binding.cardMainError.animate().alpha(0f)
                        .setDuration(shortAnimationDuration.toLong())
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                binding.cardMainError.visibility = View.GONE
                            }
                        })
                }

                NetworkStatus.Disconnected -> {
                    binding.textMainError.text = getString(R.string.disconnected)
                    binding.textMainError.setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity, R.color.text_error
                        )
                    )
                    binding.cardMainError.setCardBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity, R.color.error_bg
                        )
                    )
                    binding.cardMainError.apply {
                        alpha = 0f
                        visibility = View.VISIBLE

                        // Animate the content view to 100% opacity, and clear any animation
                        // listener set on the view.
                        animate().alpha(1f).setDuration(shortAnimationDuration.toLong())
                            .setListener(null)
                    }
                }

                NetworkStatus.Unknown -> {

                }
            }
        }
    }
}