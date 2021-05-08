package com.sean.publisher.postDialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class PostDialogViewModel : ViewModel() {


    val title = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val content = MutableLiveData<String>()
//    val date = Calendar.getInstance().timeInMillis
//    val article: MutableMap<String, Any> = HashMap()

    fun addData() {
        val articles = FirebaseFirestore.getInstance()
                .collection("articles")
        val document = articles.document()
        val data = hashMapOf(
                "author" to hashMapOf(
                        "email" to "wayne@school.appworks.tw",
                        "id" to "waynechen323",
                        "name" to "AKA小安老師"
                ),
                "title" to title,
                "content" to content,
                "createdTime" to Calendar.getInstance().timeInMillis,
                "id" to document.id,
                "category" to category
        )
        document.set(data)
    }


//    val articles = FirebaseFirestore.getInstance()
//            .collection("articles")
//
//    fun addArticle() {
//
//        val document = articles.document()
//        val data = hashMapOf(
//                "author" to hashMapOf(
//                        "email" to "wayne@school.appworks.tw",
//                        "id" to "waynechen323",
//                        "name" to "AKA小安老師"
//                ),
//                "title" to title.value,
//                "content" to content.value,
//                "createdTime" to Calendar.getInstance()
//                        .timeInMillis,
//                "id" to document.id,
//                "category" to category.value
//        )
//        document.set(data)
//    }
}