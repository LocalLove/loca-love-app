package ru.nsu.localove.security.restore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.nsu.localove.databinding.FragmentNewPasswordBinding

class NewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setNewPasswordButton.setOnClickListener {
            val action = NewPasswordFragmentDirections.actionNewPasswordToLoginFragment()
            findNavController().navigate(action)
        }
    }
}