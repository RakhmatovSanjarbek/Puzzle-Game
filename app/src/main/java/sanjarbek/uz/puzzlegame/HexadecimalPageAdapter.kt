package sanjarbek.uz.puzzlegame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HexadecimalPageAdapter(
    private val onClick: (puzzleModel: PuzzleModel, emptyPuzzleModel: PuzzleModel) -> Unit
) : RecyclerView.Adapter<HexadecimalPageAdapter.PuzzleViewHolde>() {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuzzleViewHolde {
        val iteamView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_puzzle_piece_hexadecimal, parent, false)
        return PuzzleViewHolde(iteamView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PuzzleViewHolde, position: Int) {
        dataList[position].cardPosition = position
        holder.bind(dataList[position])
    }

    inner class PuzzleViewHolde(
        private val iteamView: View
    ) : RecyclerView.ViewHolder(iteamView) {

        private lateinit var textNumber: TextView

        fun bind(puzzleModel: PuzzleModel) {
            textNumber = iteamView.findViewById(R.id.text_number)
            textNumber.text = puzzleModel.cardNumber.toString()

            val emptyPuzzleModel: PuzzleModel? = dataList.find { it.cardNumber == -1 }

            iteamView.setOnClickListener {
                when {
                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 0 && (adapterPosition == 1 || adapterPosition == 4) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 1 && (adapterPosition == 0 || adapterPosition == 2 || adapterPosition == 5) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 2 && (adapterPosition == 1 || adapterPosition == 3 || adapterPosition == 6) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 3 && (adapterPosition == 2 || adapterPosition == 7) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 4 && (adapterPosition == 0 || adapterPosition == 5 || adapterPosition == 8) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 5 && (adapterPosition == 4 || adapterPosition == 6 || adapterPosition == 9 || adapterPosition == 1) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 6 && (adapterPosition == 2 || adapterPosition == 5 || adapterPosition == 7 || adapterPosition == 10) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 7 && (adapterPosition == 3 || adapterPosition == 6 || adapterPosition == 11) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 8 && (adapterPosition == 4 || adapterPosition == 9 || adapterPosition == 12) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 9 && (adapterPosition == 5 || adapterPosition == 8 || adapterPosition == 10 || adapterPosition == 13) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 10 && (adapterPosition == 6 || adapterPosition == 9 || adapterPosition == 11 || adapterPosition == 14) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 11 && (adapterPosition == 7 || adapterPosition == 10 || adapterPosition == 15) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 12 && (adapterPosition == 8 || adapterPosition == 13) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 13 && (adapterPosition == 9 || adapterPosition == 12 || adapterPosition == 14) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 14 && (adapterPosition == 10 || adapterPosition == 13 || adapterPosition == 15) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 15 && (adapterPosition == 11 || adapterPosition == 14) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }
                }
            }

            iteamView.visibility =
                if (puzzleModel.cardNumber == -1) View.INVISIBLE else View.VISIBLE
        }
    }
}

