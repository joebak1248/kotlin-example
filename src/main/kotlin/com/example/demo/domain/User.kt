package com.example.demo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User (
    @Id
    @GeneratedValue
    var id: Int,
    var name: String,
    var address: String
)