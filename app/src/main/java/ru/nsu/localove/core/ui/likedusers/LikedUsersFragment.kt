package ru.nsu.localove.core.ui.likedusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentLikedUsersBinding

class LikedUsersFragment : Fragment() {
    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLikedUsersBinding.inflate(inflater, container, false)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_liked_users,
                R.id.navigation_nearby_users,
                R.id.navigation_profile
            )
        )
        binding.toolbarLikedUsers.setupWithNavController(
            findNavController(),
            appBarConfiguration
        )

        return binding.root
    }
}