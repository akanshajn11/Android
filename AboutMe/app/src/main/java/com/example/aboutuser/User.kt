package com.example.aboutuser

data class User(
    var name: String = "",
    var nickname: String = "",
    var email: String = "",
    var tech: String = "",
    var city: String = "",
    var gender: String = "Male",
    var userType: String = "Student",
    var active: String = "No",
    var projects: String = ""
)