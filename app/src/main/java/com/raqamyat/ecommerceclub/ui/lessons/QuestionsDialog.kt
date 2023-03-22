package com.raqamyat.ecommerceclub.ui.lessons

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ErrorDialogFragmentBinding
import com.raqamyat.ecommerceclub.databinding.QuestionsDialogBinding
import java.util.*

class QuestionsDialog(private  var listener: QuestionsListener) : DialogFragment() {
    private lateinit var binding: QuestionsDialogBinding
//    private lateinit var listener: QuestionsListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }


//    fun newInstance(listener: QuestionsListener, bundle: Bundle?): QuestionsDialog {
//        this.listener = listener
//        val f = QuestionsDialog()
//        f.arguments = bundle
//        return f
//    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window
            ?.attributes?.windowAnimations = R.style.DialogStyle
        dialog!!.window!!.setGravity(Gravity.CENTER)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
        binding = QuestionsDialogBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.close.setOnClickListener{dismiss()}
        binding.send.setOnClickListener{
            listener.onAddQuestionClicked(binding.question.text.toString())
            dismiss()
        }

        return binding.root
    }

    interface QuestionsListener {
        fun onAddQuestionClicked(question: String)
    }
}