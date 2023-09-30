package com.example.kotlinflyway.entity

import javax.persistence.*

@Entity
@Table(name = "person")
class PersonEntity(
    val name: String
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
}