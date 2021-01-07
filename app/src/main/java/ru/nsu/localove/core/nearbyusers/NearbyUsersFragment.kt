package ru.nsu.localove.core.nearbyusers

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
import ru.nsu.localove.databinding.FragmentNearbyUsersBinding

class NearbyUsersFragment : Fragment() {

    private val nearbyUsersViewModel: NearbyUsersViewModel by viewModels()

    private lateinit var binding: FragmentNearbyUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNearbyUsersBinding.inflate(inflater, container, false)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_liked_users,
                R.id.navigation_nearby_users,
                R.id.navigation_profile
            )
        )
        binding.toolbarNearbyUsers.setupWithNavController(
            findNavController(),
            appBarConfiguration
        )

        nearbyUsersViewModel.text.observe(viewLifecycleOwner, {
            binding.tmpText.text = it
        })
        return binding.root

    }
}