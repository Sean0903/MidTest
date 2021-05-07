package com.sean.publisher.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sean.publisher.R
import com.sean.publisher.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(){

    val viewModel: ArticleViewModel by lazy {
        ViewModelProvider(this).get(ArticleViewModel::class.java)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentArticleBinding.inflate(inflater, container,
                false)

        val adapter = ArticleAdapter()
        binding.recyclerviewArticle.adapter = adapter

        viewModel.article.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
            }
        })

        //使用navigation跳轉至dialog
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_articleFragment_to_postDialog)
        }

        return binding.root

    }
}