package com.thing.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thing.data.GitRepoIssueResponse
import com.thing.databinding.ListGitIssueBinding

class GitIssueListAdapter(private val item: ArrayList<GitRepoIssueResponse>) : RecyclerView.Adapter<GitIssueListAdapter.GitIssueListAdapterViewHolder>() {


    interface ItemClickListener {
        fun onClick(gitRepoIssueResponse: GitRepoIssueResponse?)
    }

    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitIssueListAdapterViewHolder {
        val view = ListGitIssueBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GitIssueListAdapterViewHolder(view)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: GitIssueListAdapterViewHolder, position: Int) {
        if(position == 4){
            holder.thingFlow()
        }else holder.bind(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }


    inner class GitIssueListAdapterViewHolder(view: ListGitIssueBinding) : RecyclerView.ViewHolder(view.root) {

        private val tvIssue = view.tvIssue
        private val ivThings = view.ivThings
        val requestManager = Glide.with(this.itemView.context)

        @SuppressLint("SetTextI18n")
        fun bind(data:GitRepoIssueResponse) {
            try {
                ivThings.visibility = View.GONE
                tvIssue.text = "${data.number}:${data.title}"
                tvIssue.setOnClickListener {
                    itemClickListener.onClick(data)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        fun thingFlow(){
           val url = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
            ivThings.visibility = View.VISIBLE
            requestManager.load(url).into(ivThings)
            ivThings.setOnClickListener {
                itemClickListener.onClick(null)
            }
        }
    }
}