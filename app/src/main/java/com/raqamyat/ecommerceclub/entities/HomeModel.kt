package com.raqamyat.ecommerceclub.entities

data class HomeModel(
    var blogs: List<BlogsModel>? = null,
    var certificates: List<Any>? = null,
    var last_episode: LastEpisode? = null,
    var watched_episodes_count: String? = null
)
