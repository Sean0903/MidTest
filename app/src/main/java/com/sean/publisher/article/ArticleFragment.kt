package com.sean.publisher.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sean.publisher.MobileNavigationDirections
import com.sean.publisher.R
import com.sean.publisher.data.Article
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

//        val mock = Article("期中考已經結束啦", "咒術迴戰死好多人", "時尚")
//        val mock2 = Article("現在弄也太晚", "富奸不讓庫拉皮卡下船", "明星")
//        val mockList = listOf( mock, mock2,mock, mock2)
//
//        adapter.submitList(mockList)

        viewModel.article.observe(viewLifecycleOwner, Observer {
            Log.d("sean", "infromation in view = $it")
            adapter.submitList(it)
        })

//        //使用navigation跳轉至dialog
//        viewModel.navigateToPublisher.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                findNavController().navigate(MobileNavigationDirections.actionArticleFragmentToPostDialog())
//                viewModel.onPublisherNavigated()
//            }
//        })

        //使用navigation跳轉至dialog
        binding.toDialog.setOnClickListener {
            findNavController().navigate(MobileNavigationDirections.actionArticleFragmentToPostDialog())
//            findNavController().navigate(R.id.action_articleFragment_to_postDialog)
        }

        return binding.root

    }
}