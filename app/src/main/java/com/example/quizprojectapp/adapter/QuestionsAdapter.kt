package com.example.quizprojectapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionsModel
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.ItemQuestionsBinding

class QuestionsAdapter : ListAdapter<QuestionsModel, QuestionsAdapter.QuestionsViewHolder>(QuestionsDiff()) {

    private val answeredQuestions = mutableSetOf<Int>()
    private val selectedAnswers = mutableMapOf<Int, Int>()

    private var onAnswerClickListener: OnAnswerClickListener? = null

    interface OnAnswerClickListener {
        fun onAnswerClick(selectedIndex: Int)
    }

    fun setOnAnswerClickListener(listener: OnAnswerClickListener) {
        onAnswerClickListener = listener
    }

    inner class QuestionsViewHolder(val binding: ItemQuestionsBinding) : RecyclerView.ViewHolder(binding.root) {
        private var correctAnswerIndex: Int = 0
        private var isAnswered: Boolean = false

        init {
            binding.linerA.setOnClickListener { onAnswerClick(0) }
            binding.linerB.setOnClickListener { onAnswerClick(1) }
            binding.linerC.setOnClickListener { onAnswerClick(2) }
            binding.linerD.setOnClickListener { onAnswerClick(3) }
        }

        private fun onAnswerClick(selectedIndex: Int) {
            if (!isAnswered) {
                isAnswered = true
                answeredQuestions.add(adapterPosition)

                selectedAnswers[adapterPosition] = selectedIndex

                if (selectedAnswers[adapterPosition] == correctAnswerIndex) {
                    setAnswerBackground(selectedIndex, R.color.green)
                } else {
                    setAnswerBackground(selectedIndex, R.color.red)
                }

                onAnswerClickListener?.onAnswerClick(selectedIndex)
            }
        }

        private fun setAnswerBackground(selectedIndex: Int, colorResource: Int) {
            binding.linerA.setBackgroundResource(if (selectedIndex == 0) colorResource else R.color.white)
            binding.linerB.setBackgroundResource(if (selectedIndex == 1) colorResource else R.color.white)
            binding.linerC.setBackgroundResource(if (selectedIndex == 2) colorResource else R.color.white)
            binding.linerD.setBackgroundResource(if (selectedIndex == 3) colorResource else R.color.white)
        }

        @SuppressLint("ResourceAsColor")
        fun bind(model: QuestionsModel) {
            binding.tvQuestions.text = model.questions
            binding.tvAnswerA.text = model.answer[0]
            binding.tvAnswerB.text = model.answer[1]
            binding.tvAnswerC.text = model.answer[2]
            binding.tvAnswerD.text = model.answer[3]

            correctAnswerIndex = model.currentAnswer.toInt()
            isAnswered = answeredQuestions.contains(adapterPosition)

            if (isAnswered) {
                if (correctAnswerIndex == selectedAnswers[adapterPosition]) {
                    setAnswerBackground(correctAnswerIndex, R.color.green)
                } else {
                    setAnswerBackground(selectedAnswers[adapterPosition] ?: -1, R.color.red)
                }
            } else {
                setAnswerBackground(-1, R.color.white)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(ItemQuestionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun clearAnsweredQuestions() {
        answeredQuestions.clear()
        selectedAnswers.clear()
        notifyDataSetChanged()
    }
}

class QuestionsDiff : DiffUtil.ItemCallback<QuestionsModel>() {
    override fun areItemsTheSame(oldItem: QuestionsModel, newItem: QuestionsModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuestionsModel, newItem: QuestionsModel): Boolean {
        return oldItem == newItem
    }
}