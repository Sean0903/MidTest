package com.sean.publisher.article

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
//import com.google.firebase.firestore.FirebaseFirestore
import com.sean.publisher.data.Article
import java.util.*


class ArticleViewModel : ViewModel() {


    var db = FirebaseFirestore.getInstance()


    private val _article = MutableLiveData<List<Article>>()

    val article: LiveData<List<Article>>
        get() = _article


    /**
     * For catch data
     */
    private val citiesRef = db.collection("articles")
    private val list = citiesRef.orderBy("createdTime", Query.Direction.DESCENDING)
    var defaultData = mutableListOf<Article>()
    var data = mutableListOf<Article>()

    private fun getData() {
        Log.d("sean", "inti data = $data")

        db.collection("articles")
            .orderBy("createdTime", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("sean", "${document.id} -> ${document.data}")
                    data.add(
                        Article(
                            title = document.data["title"].toString(),
                            category = document.data["category"].toString(),
                            content = document.data["content"].toString()
                        )
                    )
                    Log.d("sean", "data = $data")
                    break
                }
                _article.value = data
                Log.d("sean", "final = $data")
                Log.d("sean", "_article.value = ${_article.value}")

            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }


    /**
     * Observe data instantly
     */
    val personal =
        db.collection("articles")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("sean", "listen:error", e)
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            Log.d(
                                "sean",
                                "New Invitation Card: ${dc.document.data}"
                            )
                            var mock123 = dc.document.data
                            Log.d("TAG", "mock = $mock123")
                            getData()
                        }
                        DocumentChange.Type.MODIFIED -> {
                            Log.d(
                                "TAG",
                                "Changed Data: ${dc.document.data}"
                            )
                        }
                        DocumentChange.Type.REMOVED -> Log.d(
                            "TAG",
                            "Removed Invitation Card: ${dc.document.data}"
                        )
                    }
                }
            }
}