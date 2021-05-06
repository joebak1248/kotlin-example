package com.example.demo.domain

import javax.persistence.*

@Entity
@Table(name = "user")
data class User (
    var name: String,
    var address: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}