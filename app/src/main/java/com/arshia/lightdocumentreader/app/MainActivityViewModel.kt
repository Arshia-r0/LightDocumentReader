package com.arshia.lightdocumentreader.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    ldrDataRepository: LDRDataRepository,
) : ViewModel() {

    val userData = ldrDataRepository.ldrData
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5000)
        )

}
