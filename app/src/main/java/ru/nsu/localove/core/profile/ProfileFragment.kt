package ru.nsu.localove.core.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_liked_users,
                R.id.navigation_nearby_users,
                R.id.navigation_profile
            )
        )

        binding.toolbarLayout.setupWithNavController(
            binding.toolbar,
            findNavController(),
            appBarConfiguration
        )
        binding.toolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
        binding.toolbarLayout.setExpandedTitleColor(Color.WHITE)
        profileViewModel.user.observe(viewLifecycleOwner) {
            binding.toolbar.title = it.login
            binding.profileUserName.text = it.name
            binding.profileUserAge.text = it.age.toString()
            binding.profileUserStatus.text = it.status

            Glide.with(this)
                .load("http://10.0.2.2:8080/pictures/${it.pictureIds[0]}")
                .into(binding.detailImage)
        }

        binding.detailImage.setImageResource(R.drawable.ic_launcher_background)

        return binding.root
    }
}