package ru.nsu.localove.core.nearbyusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.localove.api.user.Gender
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentNearbyUsersBinding
import ru.nsu.localove.security.login.LoginFragmentDirections
import ru.nsu.localove.security.login.LoginState

@AndroidEntryPoint
class NearbyUsersFragment : Fragment() {

    private val nearbyUsersViewModel: NearbyUsersViewModel by viewModels()

    private lateinit var binding: FragmentNearbyUsersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNearbyUsersBinding.inflate(inflater, container, false)

        val profileCardAdapter = ProfileCardAdapter {}
        binding.nearbyUsersRecyclerView.adapter = profileCardAdapter
        nearbyUsersViewModel.nearbyUsers.observe(viewLifecycleOwner) {
            profileCardAdapter.dataSet = it
            profileCardAdapter.notifyDataSetChanged()
        }

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

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nearbyUsersViewModel.refreshNearbyUsers()
    }
}