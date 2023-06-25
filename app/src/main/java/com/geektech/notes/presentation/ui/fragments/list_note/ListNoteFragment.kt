package com.geektech.notes.presentation.ui.fragments.list_note

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.notes.R
import com.geektech.notes.databinding.FragmentListNoteBinding
import com.geektech.notes.domain.model.Note
import com.geektech.notes.presentation.base.BaseFragment
import com.geektech.notes.presentation.ui.fragments.list_note.adapter.ListNoteAdapter
import com.geektech.notes.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteFragment : BaseFragment(R.layout.fragment_list_note) {

    private val binding by viewBinding(FragmentListNoteBinding::bind)
    private val adapter by lazy { ListNoteAdapter(this::deleteNote, this::updateNote) }
    private val viewModel by viewModels<ListNoteViewModel>()

    override fun setupRequests() {
        getAllNotes()
    }

    override fun setupObservers() {
        viewModel.getAllNotesState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                adapter.addList(it)
            }
        )

        viewModel.deleteNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                getAllNotes()
            }
        )
    }

    override fun initialize() {
        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvNotes.adapter = adapter
    }

    private fun deleteNote(note: com.geektech.notes.domain.model.Note) {
        viewModel.deleteNote(note)
    }

    override fun initClickListeners() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_createNoteFragment)
        }
    }

    private fun getAllNotes() {
        viewModel.getAllNotes()
    }

    private fun updateNote(note: com.geektech.notes.domain.model.Note) {
        val bundle = Bundle()
        bundle.putSerializable(KEY, note)
        findNavController().navigate(R.id.action_listNoteFragment_to_createNoteFragment, bundle)
    }

    companion object {
        const val KEY = "note"
    }
}