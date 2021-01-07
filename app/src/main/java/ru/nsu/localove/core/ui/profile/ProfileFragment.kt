package ru.nsu.localove.core.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentProfileBinding

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

        profileViewModel.userName.observe(viewLifecycleOwner, {
            binding.toolbar.title = it
        })

        binding.toolbarLayout.setupWithNavController(
            binding.toolbar,
            findNavController(),
            appBarConfiguration
        )

        binding.detailImage.setImageResource(R.drawable.ic_launcher_background)

        return binding.root
    }
}