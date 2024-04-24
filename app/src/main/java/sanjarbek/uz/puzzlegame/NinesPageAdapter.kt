package sanjarbek.uz.puzzlegame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NinesPageAdapter(
    private val onClick: (puzzleModel: PuzzleModel, emptyPuzzleModel: PuzzleModel) -> Unit,
) : RecyclerView.Adapter<NinesPageAdapter.PuzzleViewHolder>() {

    val dataList = mutableListOf<PuzzleModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun loadData(newList: List<PuzzleModel>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }


    fun changeItem(currentItem: PuzzleModel, emptyItem: PuzzleModel) {
        val currentItemPosition = currentItem.cardPosition
        val emptyItemPosition = emptyItem.cardPosition
        dataList[currentItemPosition] = emptyItem
        dataList[emptyItemPosition] = currentItem
        notifyItemChanged(currentItemPosition)
        notifyItemChanged(emptyItemPosition)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puzzle_piece_nines, parent, false)
        return PuzzleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        dataList[position].cardPosition = position
        holder.bind(dataList[position])
    }

    inner class PuzzleViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private lateinit var textNumber: TextView

        fun bind(puzzleModel: PuzzleModel) {
            textNumber = itemView.findViewById(R.id.text_number)
            textNumber.text = puzzleModel.cardNumber.toString()

            val emptyPuzzleModel: PuzzleModel? = dataList.find { it.cardNumber == -1 }

            itemView.setOnClickListener {
                when {
                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 0 && (adapterPosition == 1 || adapterPosition == 3) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 1 && (adapterPosition == 0 || adapterPosition == 2 || adapterPosition == 4) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 2 && (adapterPosition == 1 || adapterPosition == 5) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 3 && (adapterPosition == 0 || adapterPosition == 4 || adapterPosition == 6) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 4 && (adapterPosition == 1 || adapterPosition == 3 || adapterPosition == 5 || adapterPosition == 7) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 5 && (adapterPosition == 2 || adapterPosition == 4 || adapterPosition == 8) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 6 && (adapterPosition == 3 || adapterPosition == 7) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 7 && (adapterPosition == 4 || adapterPosition == 6 || adapterPosition == 8) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 8 && (adapterPosition == 5 || adapterPosition == 7) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }
                }
            }

            itemView.visibility =
                if (puzzleModel.cardNumber == -1) View.INVISIBLE else View.VISIBLE
        }
    }
}
