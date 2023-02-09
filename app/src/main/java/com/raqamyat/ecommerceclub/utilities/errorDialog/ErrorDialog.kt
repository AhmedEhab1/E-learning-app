package com.raqamyat.ecommerceclub.utilities.errorDialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.databinding.ErrorDialogFragmentBinding
import java.util.*

class ErrorDialog() : DialogFragment() {
    private lateinit var binding: ErrorDialogFragmentBinding
    private var listener: ErrorDialogListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun newInstance(listener: ErrorDialogListener?, bundle: Bundle?): ErrorDialog {
        if (listener != null) this.listener = listener
        val f = ErrorDialog()
        f.arguments = bundle
        return f
    }


    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window
            ?.getAttributes()?.windowAnimations = R.style.DialogStyle
        dialog!!.window!!.setGravity(Gravity.CENTER)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
        binding = ErrorDialogFragmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        var message: String? = null
        var titleText: String? = null
        if (arguments != null) {
            message = requireArguments().getString("message")
            binding.body.text = message
            if (requireArguments().getString("title") != null) {
                titleText = requireArguments().getString("title")
                binding.title.text = titleText
            }
        } else {
            message = requireActivity().resources.getString(R.string.error_message)
            binding.body.text = message
        }
        if (listener != null) {
            binding.done.visibility = View.VISIBLE
        }
        binding.done.setOnClickListener(View.OnClickListener {
            Objects.requireNonNull(dialog)?.dismiss()
            listener?.onConfirm()
        })
        binding.cancel.setOnClickListener(View.OnClickListener {
            Objects.requireNonNull(dialog)?.dismiss()
        })
        return binding.root
    }
}