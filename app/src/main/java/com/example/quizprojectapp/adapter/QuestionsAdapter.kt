package com.example.quizprojectapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.QuestionsModel
import com.example.quizprojectapp.R
import com.example.quizprojectapp.databinding.ItemQuestionsBinding

class QuestionsAdapter : ListAdapter<QuestionsModel, QuestionsAdapter.QuestionsViewHolder>(QuestionsDiff()) {

    private val answeredQuestions = mutableSetOf<Int>()
    private var onAnswerClickListener: OnAnswerClickListener? = null

    interface OnAnswerClickListener {
        fun onAnswerClick(selectedIndex: Int)
    }

    fun setOnAnswerClickListener(listener: OnAnswerClickListener) {
        onAnswerClickListener = listener
    }

    inner class QuestionsViewHolder(val binding: ItemQuestionsBinding) : RecyclerView.ViewHolder(binding.root) {
        private var correctAnswerIndex: Int = -1
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

                if (correctAnswerIndex == selectedIndex) {
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

            correctAnswerIndex = model.currentAnswer.toInt() - 1
            isAnswered = answeredQuestions.contains(adapterPosition)
            if (isAnswered) {
                setAnswerBackground(correctAnswerIndex, if (correctAnswerIndex ==correctAnswerIndex ) R.color.green else R.color.red)
            } else {
                resetBackground()
            }
        }

        private fun resetBackground() {
            binding.linerA.setBackgroundResource(R.color.white)
            binding.linerB.setBackgroundResource(R.color.white)
            binding.linerC.setBackgroundResource(R.color.white)
            binding.linerD.setBackgroundResource(R.color.white)
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