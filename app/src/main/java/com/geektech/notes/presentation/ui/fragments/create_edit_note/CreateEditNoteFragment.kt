package com.geektech.notes.presentation.ui.fragments.create_edit_note

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.notes.R
import com.geektech.notes.databinding.FragmentCreateEditNoteBinding
import com.geektech.notes.domain.model.Note
import com.geektech.notes.presentation.base.BaseFragment
import com.geektech.notes.presentation.ui.fragments.list_note.ListNoteFragment.Companion.KEY
import com.geektech.notes.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditNoteFragment : BaseFragment(R.layout.fragment_create_edit_note) {

    private val binding by viewBinding(FragmentCreateEditNoteBinding::bind)
    private val viewModel by viewModels<CreateEditNoteViewModel>()
    private var note: com.geektech.notes.domain.model.Note? = null
    override fun initClickListeners() {
        note = arguments?.getSerializable(KEY) as com.geektech.notes.domain.model.Note?
        binding.etTitle.setText(note?.title)
        binding.etDescription.setText(note?.description)
        if (note == null) {
            binding.btnSave.setOnClickListener {
                viewModel.createNote(
                    com.geektech.notes.domain.model.Note(
                        title = binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString()
                    )
                )
            }
        } else {

            binding.btnSave.text = "Update"
            binding.btnSave.setOnClickListener {
                note!!.title = binding.etTitle.text.toString()
                note!!.description = binding.etDescription.text.toString()
                viewModel.updateNote(
                    note!!
                )
            }

        }
    }

    override fun setupObservers() {
        viewModel.createNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )

        viewModel.updateNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )
    }
}