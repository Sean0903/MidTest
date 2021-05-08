package com.sean.publisher.postDialog


import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.sean.publisher.databinding.DialogBinding
import java.util.*

class PostDialog : DialogFragment() {

    var db = FirebaseFirestore.getInstance()


    private val viewModel: PostDialogViewModel by lazy {
        ViewModelProvider(this).get(PostDialogViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = DialogBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.dialogTitle.doOnTextChanged { text, start, before, count ->
            viewModel.title.value = text.toString()
            Log.d("sean", "viewModel.title.value = ${viewModel.title.value}")
        }

        binding.dialogCategory.doOnTextChanged { text, start, before, count ->
            viewModel.category.value = text.toString()
            Log.d("sean", "viewModel.category.value = ${viewModel.category.value}")
        }

        binding.dialogContent.doOnTextChanged { text, start, before, count ->
            viewModel.content.value = text.toString()
            Log.d("sean", "viewModel.content.value = ${viewModel.content.value}")
        }


        binding.dialogPost.setOnClickListener {

            if (viewModel.content.value.isNullOrBlank() ||
                    viewModel.category.value.isNullOrBlank() ||
                    viewModel.title.value.isNullOrBlank()) {
                Toast.makeText(context, "請在各欄輸入完整訊息", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addData()
            }
        }

        return binding.root
    }


//    private fun send() {
//        //測試
//        val washingtonRef =
//            db.collection("author").document("2021")
//        washingtonRef.update("title", FieldValue.arrayUnion(viewModel.title.value))
//        washingtonRef.update("category", FieldValue.arrayUnion(viewModel.category.value))
//        washingtonRef.update("content", FieldValue.arrayUnion(viewModel.content.value))
//    }
}





















