package com.arshia.lightdocumentreader.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arshia.lightdocumentreader.core.data.repository.DocumentRepository
import com.arshia.lightdocumentreader.core.data.repository.LDRDataRepository
import com.arshia.lightdocumentreader.core.model.LDRData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    ldrDataRepository: LDRDataRepository,
    documentRepository: DocumentRepository,
) : ViewModel() {

    val uiState = ldrDataRepository.ldrData
        .map { MainActivityUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = MainActivityUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000)
        )

    val documents = documentRepository.documents
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000)
        )

}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val data: LDRData) : MainActivityUiState
}
