package ru.nsu.localove.security.restore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.nsu.localove.databinding.FragmentPasswordRestoreBinding

class PasswordRestoreFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRestoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordRestoreBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.mailSendButton.setOnClickListener {
            val action = PasswordRestoreFragmentDirections.actionPasswordRestoreFragmentToPasswordRestoreFragment2()
            findNavController().navigate(action)
        }
    }
}