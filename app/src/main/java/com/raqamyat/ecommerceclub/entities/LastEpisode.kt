package com.raqamyat.ecommerceclub.entities

data class LastEpisode(
    val description: String,
    val `file`: Any,
    val id: Int,
    val last_stopped_time: Int,
    val questions: List<Question>,
    val speaker: Speaker,
    val status: String,
    val time: String,
    val title: String,
    val video_id: String
)