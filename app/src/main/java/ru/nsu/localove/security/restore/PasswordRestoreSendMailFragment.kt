package ru.nsu.localove.security.restore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.nsu.localove.databinding.FragmentPasswordRestoreSendMailBinding

class PasswordRestoreSendMailFragment : Fragment() {
    private lateinit var binding: FragmentPasswordRestoreSendMailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordRestoreSendMailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.continueButton.setOnClickListener {
            val action = PasswordRestoreSendMailFragmentDirections.actionMailSendToNewPassword()
            findNavController().navigate(action)
        }
    }
}