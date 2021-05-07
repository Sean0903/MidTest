package com.sean.publisher.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sean.publisher.data.Article
import java.util.*


class ArticleViewModel :ViewModel(){


    private val _article = MutableLiveData<List<Article>>()

    val article: LiveData<List<Article>>
        get() = _article

    var db = FirebaseFirestore.getInstance()

}