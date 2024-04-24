package sanjarbek.uz.puzzlegame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TwentyFivePageAdapter(
    private val onClick: (puzzleModel: PuzzleModel, emptyPuzzleModel: PuzzleModel) -> Unit,
) : RecyclerView.Adapter<TwentyFivePageAdapter.PuzzleViewHolder>() {

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
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puzzle_piece_twenty_five,parent,false)
        return PuzzleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: PuzzleViewHolder, position: Int) {
        dataList[position].cardPosition=position
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
                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 0 && (adapterPosition == 1 || adapterPosition == 5) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 1 && (adapterPosition == 0 || adapterPosition == 2 || adapterPosition == 6) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 2 && (adapterPosition == 1 || adapterPosition == 3 || adapterPosition == 7) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 3 && (adapterPosition == 2 || adapterPosition == 4 || adapterPosition == 8) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 4 && (adapterPosition == 3 || adapterPosition == 9) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 5 && (adapterPosition == 0 || adapterPosition == 6 || adapterPosition == 10) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 6 && (adapterPosition == 1 || adapterPosition == 5 || adapterPosition == 7 || adapterPosition == 11) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 7 && (adapterPosition == 2 || adapterPosition == 6 || adapterPosition == 8 || adapterPosition == 12) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 8 && (adapterPosition == 3 || adapterPosition == 7 || adapterPosition == 9 || adapterPosition == 13) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 9 && (adapterPosition == 4 || adapterPosition == 8 || adapterPosition == 14) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 10 && (adapterPosition == 5 || adapterPosition == 11 || adapterPosition == 15) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 11 && (adapterPosition == 6 || adapterPosition == 10 || adapterPosition == 12 || adapterPosition == 16) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 12 && (adapterPosition == 7 || adapterPosition == 11 || adapterPosition == 13 || adapterPosition == 17) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 13 && (adapterPosition == 8 || adapterPosition == 12 || adapterPosition == 14 || adapterPosition == 18) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 14 && (adapterPosition == 9 || adapterPosition == 13 || adapterPosition == 19) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 15 && (adapterPosition == 10 || adapterPosition == 16 || adapterPosition == 20) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 16 && (adapterPosition == 11 || adapterPosition == 15 || adapterPosition == 17 || adapterPosition == 21) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 17 && (adapterPosition == 12 || adapterPosition == 16 || adapterPosition == 18 || adapterPosition == 22) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 18 && (adapterPosition == 13 || adapterPosition == 17 || adapterPosition == 19 || adapterPosition == 23) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 19 && (adapterPosition == 14 || adapterPosition == 18 || adapterPosition == 24) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 20 && (adapterPosition == 15 || adapterPosition == 21) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 21 && (adapterPosition == 16 || adapterPosition == 20 || adapterPosition == 22) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 22 && (adapterPosition == 17 || adapterPosition == 21 || adapterPosition == 23) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 23 && (adapterPosition == 18 || adapterPosition == 22 || adapterPosition == 24) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }

                    emptyPuzzleModel?.cardNumber == -1 && emptyPuzzleModel.cardPosition == 24 && (adapterPosition == 19 || adapterPosition == 23) -> {
                        onClick(puzzleModel, emptyPuzzleModel)
                    }
                }
            }
            itemView.visibility = if (puzzleModel.cardNumber == -1) View.INVISIBLE else View.VISIBLE
        }
    }

}