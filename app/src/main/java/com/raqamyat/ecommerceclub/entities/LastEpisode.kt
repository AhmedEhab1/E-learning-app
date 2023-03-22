package com.raqamyat.ecommerceclub.entities

data class LastEpisode(
    val description: String? = null,
    val `file`: Any? = null,
    val id: Int? = null,
    val last_stopped_time: Float? = null,
    val questions: List<Question>? = null,
    val speaker: Speaker? = null,
    var status: String? = null,
    val time: String? = null,
    val title: String? = null,
    val video_id: String? = null
)