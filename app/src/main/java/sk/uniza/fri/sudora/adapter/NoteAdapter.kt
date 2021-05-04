package sk.uniza.fri.sudora.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.uniza.fri.sudora.ListType
import sk.uniza.fri.sudora.ListType.ARCHIVE
import sk.uniza.fri.sudora.ListType.TRASH
import sk.uniza.fri.sudora.NoteListViewModel
import sk.uniza.fri.sudora.databinding.NoteViewBinding
import sk.uniza.fri.sudora.notes.Note
import java.util.*
import sk.uniza.fri.sudora.ListType.NOTE as NOTE1


class NoteAdapter(
    private val viewModel: NoteListViewModel/*, private val list: MutableList<Note?>*/, private val listType: ListType, private val clickListener: NoteListener)
    : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteAdapterDiffCallback()){

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        try {
            //val item = list[position]
            val item = getItem(position)
            //holder.bind(list[position]!!, clickListener)
            holder.binding.noteTitleView.text = item?.noteTitle
            holder.binding.noteTextView.text = item?.noteText
            holder.moveDataToViewHolder(viewModel, item, listType)
            holder.bind(item, clickListener)

        } catch (e: NullPointerException) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.from(parent)
    }

//    override fun getItemCount(): Int {
//        return list.size
//    }

    class NoteViewHolder constructor(val binding: NoteViewBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var note : Note
        lateinit var viewModel: NoteListViewModel
        lateinit var listType: ListType

        fun bind(item: Note, clickListener: NoteListener) {
            binding.note = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteViewBinding.inflate(layoutInflater, parent, false)
                return NoteViewHolder(binding).buttonListener()
            }
        }

        private fun buttonListener(): NoteViewHolder {
            binding.btnDelete.setOnClickListener {
                when(listType){
                    NOTE1 -> {
                        viewModel.removeNote(note, NOTE1)
                        viewModel.addNote(note, TRASH)
                    }
                    ARCHIVE -> {
                        viewModel.removeNote(note, ARCHIVE)
                        viewModel.addNote(note, TRASH)
                    }
                    TRASH -> viewModel.removeNote(note, TRASH)
                }
            }
            binding.btnArchive.setOnClickListener {
                when(listType){
                    NOTE1 -> {
                        viewModel.removeNote(note, NOTE1)
                        viewModel.addNote(note, ARCHIVE)
                    }
                    ARCHIVE -> {

                    }
                    TRASH -> {
                        viewModel.removeNote(note, TRASH)
                        viewModel.addNote(note, ARCHIVE)
                    }
                }
            }
            return this
        }

        fun moveDataToViewHolder(viewModel: NoteListViewModel, note: Note?, listType: ListType) {
            this.note = note!!
            this.viewModel = viewModel
            this.listType = listType
        }
    }


    class NoteAdapterDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

}
class NoteListener(val clickListener: (noteId: UUID) -> Unit) {
    fun onClick(note: Note) = clickListener(note.noteId)
}