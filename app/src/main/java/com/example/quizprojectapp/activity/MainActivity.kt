package com.example.quizprojectapp.activity

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.ActivityMainBinding
import com.example.quizprojectapp.fragment.MainFragment
import com.example.quizprojectapp.onBoarding.ViewPager2Fragment
import com.example.quizprojectapp.onBoarding.utils.Preferences
import com.example.quizprojectapp.settingfragment.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var onBoardingCompletedKey: String

    @Inject
    lateinit var preferences: Preferences


    private val settingsViewModel: SettingViewModel by viewModels()
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBoardingCompletedKey = "onBoardingCompleted"

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            if (!preferences.isBoardingShow()) {
                supportFragmentManager.beginTransaction().add(R.id.fr_container, ViewPager2Fragment())
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fr_container, MainFragment()).commit()
            }
        }



        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true

        lifecycleScope.launch {
            settingsViewModel.music.collect {
                when (it) {
                    "yes" -> mediaPlayer.start()
                    "off" -> mediaPlayer.pause()
                    else -> {}
                }
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(onBoardingCompletedKey, preferences.isBoardingShow())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        preferences.setBoardingShow(savedInstanceState.getBoolean(onBoardingCompletedKey, false))
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
    }
}