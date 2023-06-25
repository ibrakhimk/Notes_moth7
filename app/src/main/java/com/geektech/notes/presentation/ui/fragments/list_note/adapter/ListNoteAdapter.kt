package com.geektech.notes.presentation.ui.fragments.list_note.adapter

import android.annotation.SuppressLint
import android.system.Os.remove
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geektech.notes.databinding.ItemNoteBinding
import com.geektech.notes.domain.model.Note

class ListNoteAdapter(
    private val deleteNote: (com.geektech.notes.domain.model.Note) -> Unit,
    private val updateNote: (com.geektech.notes.domain.model.Note) -> Unit
) : Adapter<ListNoteAdapter.ListNoteViewHolder>() {

    private var list = arrayListOf<com.geektech.notes.domain.model.Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNoteViewHolder {
        return ListNoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<com.geektech.notes.domain.model.Note>) {
        this.list = list as ArrayList<com.geektech.notes.domain.model.Note>
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun delete(liste: com.geektech.notes.domain.model.Note) {
        list.remove(liste)
        notifyDataSetChanged()
    }

    inner class ListNoteViewHolder(private val binding: ItemNoteBinding) :
        ViewHolder(binding.root) {
        fun bind(note: com.geektech.notes.domain.model.Note) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description

            itemView.setOnClickListener {
                updateNote(note)
            }

            itemView.setOnLongClickListener {
                deleteNote(note)
                false
            }
        }
    }
}