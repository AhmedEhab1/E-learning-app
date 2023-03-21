package com.raqamyat.ecommerceclub.ui.exam.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raqamyat.ecommerceclub.databinding.ExamQuestionItemBinding
import com.raqamyat.ecommerceclub.entities.AnswersModel
import com.raqamyat.ecommerceclub.entities.AnswersRequest
import com.raqamyat.ecommerceclub.entities.ExamModel

class ExamQuestionAdapter(private val data: ExamModel, private val context: Activity,
                          private val listener : ExamAdapter.AnswersListener)
    : RecyclerView.Adapter<ExamQuestionViewHolder>() {
    private var selectedItem : Int= -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamQuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ExamQuestionItemBinding.inflate(inflater, parent, false)
        return ExamQuestionViewHolder(binding ,  listener )
    }

    override fun onBindViewHolder(holder: ExamQuestionViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(data.answers!![position], context, position, selectedItem)
        holder.setIsRecyclable(false);

        holder.itemView.setOnClickListener{
            selectedItem = position
            listener.onAnswerClicked(AnswersRequest(data.id!! , data.answers[position].id!!))
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return data.answers!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}




