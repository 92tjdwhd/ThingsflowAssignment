package com.thing.ui.issueDetail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thing.R
import com.thing.adapter.GitIssueListAdapter
import com.thing.data.GitRepoIssueResponse
import com.thing.databinding.ActivityIssueDetailBinding
import com.thing.databinding.ActivityMainBinding
import com.thing.ui.main.MainViewModel


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class IssueDetailActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context,gitRepoIssueResponse: GitRepoIssueResponse) {
            val intent = Intent(context, IssueDetailActivity::class.java)
            intent.putExtra("name",gitRepoIssueResponse.user?.login)
            intent.putExtra("avatar_url",gitRepoIssueResponse.user?.avatar_url)
            intent.putExtra("body",gitRepoIssueResponse.body)

            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivityIssueDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue_detail)

        initView()

    }

    private fun initView() {
        val name = intent.getStringExtra("name")
        val avatar_url = intent.getStringExtra("avatar_url")
        val body = intent.getStringExtra("body")

        binding.tvUserName.text = name
        binding.tvBody.text = body
        val requestManager = Glide.with(this@IssueDetailActivity)
        requestManager.load(avatar_url).into(binding.ivUserImage)

    }

}