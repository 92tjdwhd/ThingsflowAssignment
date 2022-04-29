package com.thing.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thing.R
import com.thing.adapter.GitIssueListAdapter
import com.thing.databinding.ActivityMainBinding
import com.thing.databinding.DialogSearchBinding
import com.thing.ui.issueDetail.IssueDetailActivity


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }

        initView()
        initEvent()
    }

    private fun initView() {
        binding.rvGitIssueList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun initEvent() {
        with(viewModel) {
            updateGitIssue.observe(this@MainActivity, Observer {
                it?.let {
                    val adapter = GitIssueListAdapter(it)
                    binding.rvGitIssueList.adapter = adapter
                    adapter.setItemClickListener(gitListClickListener)
                }
            })
            moveIssueDetailEvent.observe(this@MainActivity, Observer {
                it?.let {
                    IssueDetailActivity.startActivity(this@MainActivity, it)
                }
            })

            titleClickEvent.observe(this@MainActivity, Observer {
                val dialogView = DialogSearchBinding.inflate(layoutInflater)
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                    .setView(dialogView.root)
                    .create()
                val org = dialogView.etOrg.text
                val repo = dialogView.etRepo.text
                dialogView.btn.setOnClickListener {
                    alertDialog.dismiss()
                    requestGitRepoIssues(org.toString(),repo.toString())
                }
                alertDialog.show()
            })

            moveThings.observe(this@MainActivity, Observer {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://thingsflow.com/ko/home"))
                startActivity(intent)
            })
        }
    }
}