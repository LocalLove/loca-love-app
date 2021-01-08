package ru.nsu.localove.security.registration

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.localove.api.user.Gender
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentRegistrationBinding
import ru.nsu.localove.util.afterTextChanged
import java.time.LocalDate

typealias Errors = UserInfo

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        setOnViewUpdateListeners()

        binding.registrationSignUpButton.setOnClickListener {
            registrationViewModel.register()
        }

        registrationViewModel.readyToRegister.observe(viewLifecycleOwner) {
            binding.registrationSignUpButton.isClickable = it
        }

        registrationViewModel.registrationState.observe(viewLifecycleOwner) Observer@{
            val errors = Errors()
            when (it) {
                is RegistrationState.Success -> navigateToSignIn()
                is RegistrationState.LoginAlreadyExist -> errors.login = "Already exists"
                is RegistrationState.EmailAlreadyExist -> errors.email = "Already exists"
                is RegistrationState.ServerError -> Toast
                    .makeText(activity, "Server error", Toast.LENGTH_SHORT)
                    .show()
            }
            showErrors(errors)
        }

        return binding.root
    }

    private fun navigateToSignIn() {
        Toast.makeText(
            activity,
            "Confirm your email to continue!",
            Toast.LENGTH_SHORT
        ).show()
        val action = RegistrationFragmentDirections.actionRegistrationFirstToLoginFragment()
        findNavController().navigate(action)
    }

    private fun setOnViewUpdateListeners() {
        binding.registrationEmailTextInput.validateOnTextChanged {
            UserInfo(email = it)
        }
        binding.registrationLoginTextInput.validateOnTextChanged {
            UserInfo(login = it)
        }
        binding.registrationNameTextInput.validateOnTextChanged {
            UserInfo(name = it)
        }
        binding.registrationPasswordTextInput.validateOnTextChanged {
            UserInfo(password = it)
        }
        binding.registrationPasswordConfirmationTextInput.validateOnTextChanged {
            UserInfo(passwordConfirmation = it)
        }
        binding.registrationBirthDateTextInput.setOnClickListener {
            val calendarListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val date = LocalDate.of(year, month + 1, day)
                registrationViewModel.onDataChanged(
                    UserInfo(birthDate = date)
                )
                binding.registrationBirthDateTextInput.setText(date.toString())
            }
            showDatePickerDialog(calendarListener)
        }

        binding.registrationGenderRadioGroup.setOnCheckedChangeListener { _, id ->
            registrationViewModel.onDataChanged(
                UserInfo(
                    gender = when (id) {
                        R.id.registrationMaleRadioButton -> Gender.MALE
                        else -> Gender.FEMALE
                    }
                )
            )
        }
    }

    private fun showDatePickerDialog(listener: DatePickerDialog.OnDateSetListener) {
        val now = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            listener,
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun EditText.validateOnTextChanged(
        createUserInfo: (String) -> UserInfo
    ) {
        afterTextChanged {
            val errors = Errors()
            when (registrationViewModel.onDataChanged(createUserInfo(it))) {
                is RegistrationFormState.InvalidEmail -> errors.email = "Wrong email format"
                is RegistrationFormState.InvalidLogin -> errors.login =
                    "Should contain at least 3 characters"
                is RegistrationFormState.InvalidName -> errors.name =
                    "Should contain at least 3 characters"
                is RegistrationFormState.InvalidPassword -> errors.password =
                    "Should contain at least 8 characters," +
                            " and include at least one digit"
                is RegistrationFormState.UnequalPasswords -> errors.passwordConfirmation =
                    "Unequal passwords"
                is RegistrationFormState.Valid -> {
                    Log.d("REGISTRATION", "valid:${registrationViewModel.userInfo}")
                }
            }
            showErrors(errors)
        }
    }

    private fun showErrors(errors: Errors) {
        binding.registrationPasswordConfirmationTextInputLayout.error = errors.passwordConfirmation
        binding.registrationEmailTextInputLayout.error = errors.email
        binding.registrationPasswordTextInputLayout.error = errors.password
        binding.registrationNameTextInputLayout.error = errors.name
        binding.registrationLoginTextInputLayout.error = errors.login
    }
}

