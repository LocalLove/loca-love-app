package ru.nsu.localove.core

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.R
import ru.nsu.localove.api.TokenService
import ru.nsu.localove.databinding.ActivityBottomNavBinding
import ru.nsu.localove.security.MainActivity

@AndroidEntryPoint
class BottomNavActivity : AppCompatActivity() {

    private val viewModel: BottomNavViewModel by viewModels()

    private lateinit var binding: ActivityBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(
            binding.root
        )

        if (!viewModel.isAuthorized) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val navController = findNavController(R.id.main_nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }
}


class BottomNavViewModel @ViewModelInject constructor(
    private val tokenService: TokenService
) : ViewModel() {
    val isAuthorized by lazy {
        tokenService.isAuthorized()
    }
}