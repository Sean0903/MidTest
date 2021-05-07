package com.sean.publisher.postDialog

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sean.publisher.databinding.DialogBinding

class PostDialog : DialogFragment() {

    private val viewModel: PostDialogViewModel by lazy {
        ViewModelProvider(this).get(PostDialogViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DialogBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.dialogPost.setOnClickListener {
            if (viewModel.title.value != null && viewModel.category.value != null && viewModel.content.value != null) {
                viewModel.addArticle()
            }
        }
        return binding.root
    }
}





















