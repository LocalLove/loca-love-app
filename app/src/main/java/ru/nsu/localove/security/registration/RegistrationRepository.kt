package ru.nsu.localove.security.registration

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class RegistrationRepository @Inject constructor() {

    var testHiltString = MutableLiveData<String>().apply {
        value = "HIIILT"
    }
}