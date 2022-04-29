package com.thing.data


import com.google.gson.annotations.SerializedName

data class GitRepoIssueResponse(
    @field:SerializedName("number") val number: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("user") val user: User?,
    @field:SerializedName("body") val body: String?

)

data class User(
    @field:SerializedName("login") val login: String?,
    @field:SerializedName("avatar_url") val avatar_url: String?
)

/**
 * 유저 정보 요청 데이터 클래스
 */
data class GitRepoIssueRequest(
    @field:SerializedName("org") val org: String,
    @field:SerializedName("repo") val repo: String,
)
