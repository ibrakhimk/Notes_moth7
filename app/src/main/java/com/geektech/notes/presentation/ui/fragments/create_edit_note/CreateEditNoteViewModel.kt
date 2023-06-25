package com.geektech.notes.presentation.ui.fragments.create_edit_note

import com.geektech.notes.domain.model.Note
import com.geektech.notes.domain.usecase.CreateNoteUseCase
import com.geektech.notes.domain.usecase.UpdateNoteUseCase
import com.geektech.notes.presentation.base.BaseViewModel
import com.geektech.notes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEditNoteViewModel @Inject constructor(
    private val createNoteUseCase: com.geektech.notes.domain.usecase.CreateNoteUseCase,
    private val updateNoteUseCase: com.geektech.notes.domain.usecase.UpdateNoteUseCase
) : BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun createNote(note: com.geektech.notes.domain.model.Note) {
        createNoteUseCase.createNote(note).collectData(_createNoteState)
    }

    fun updateNote(note: com.geektech.notes.domain.model.Note) {
        updateNoteUseCase.updateNote(note).collectData(_updateNoteState)
    }
}