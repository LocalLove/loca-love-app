package ru.nsu.localove.security.registration

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.localove.api.user.Gender
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.localove.R
import ru.nsu.localove.databinding.FragmentRegistrationBinding
import ru.nsu.localove.util.afterTextChanged
import java.time.LocalDate

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

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
                UserInfo(birthDate = date)
                binding.registrationBirthDateTextInput.setText(date.toString())
            }
            showDatePickerDialog(calendarListener)
        }

        binding.registrationGenderRadioGroup.setOnCheckedChangeListener { _, id ->
            UserInfo(
                gender = when (id) {
                    R.id.registrationMaleRadioButton -> Gender.MALE
                    else -> Gender.FEMALE
                }
            )
        }

        registrationViewModel.readyToRegister.observe(viewLifecycleOwner) {
            binding.registrationSignUpButton.isClickable = it
        }

        return binding.root
    }

    private fun onViewUpdate() {
        registrationViewModel
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
            when(registrationViewModel.onDataChanged(createUserInfo(it))) {
                is RegistrationState.UnequalPasswords -> binding.registrationPasswordConfirmationTextInputLayout.setError("Error")
                is RegistrationState.Valid -> {
                    Log.d("REGISTRATION", "valid:${registrationViewModel.userInfo}")
                }
            }
        }
    }
}