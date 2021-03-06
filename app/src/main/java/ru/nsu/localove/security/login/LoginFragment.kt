package ru.nsu.localove.security.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginState.Success -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToBottomNavActivity()
                    findNavController().navigate(action)
                }
                is LoginState.WrongCredentials -> {
                    Toast.makeText(activity, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginTextEdit.addTextChangedListener {
            loginViewModel.loginInfo.login = it.toString()
        }

        binding.passwordTextEdit.addTextChangedListener {
            loginViewModel.loginInfo.password = it.toString()
        }

        binding.signInButton.setOnClickListener {
            loginViewModel.signIn()
        }

        binding.signUpText.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFirst()
            findNavController().navigate(action)
        }

        binding.passwordRestoreText.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToPasswordRestoreFragment()
            findNavController().navigate(action)
        }
    }
}