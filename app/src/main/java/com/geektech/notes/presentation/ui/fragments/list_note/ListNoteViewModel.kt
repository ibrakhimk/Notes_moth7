package com.geektech.notes.presentation.ui.fragments.list_note

import com.geektech.notes.domain.model.Note
import com.geektech.notes.domain.usecase.DeleteNoteUseCase
import com.geektech.notes.domain.usecase.GetAllNotesUseCase
import com.geektech.notes.presentation.base.BaseViewModel
import com.geektech.notes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: com.geektech.notes.domain.usecase.GetAllNotesUseCase,
    private val deleteNoteUseCase: com.geektech.notes.domain.usecase.DeleteNoteUseCase
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<com.geektech.notes.domain.model.Note>>>(UIState.Empty())
    val getAllNotesState: StateFlow<UIState<List<com.geektech.notes.domain.model.Note>>> = _getAllNotesState

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState: MutableStateFlow<UIState<Unit>> = _deleteNoteState

    fun getAllNotes() {
        getAllNotesUseCase.getAllNotes().collectData(_getAllNotesState)
    }

    fun deleteNote(note: com.geektech.notes.domain.model.Note) {
        deleteNoteUseCase.deleteNote(note).collectData(_deleteNoteState)
    }
}