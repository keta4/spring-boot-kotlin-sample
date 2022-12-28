package com.example.web.entity

import jakarta.persistence.*
import java.time.LocalDateTime

// https://qiita.com/komatsuh/items/a8a81bc889c4cf88d9c5
// https://archive-blog.yagi2.dev/2017/10/04/spring-boot-kotlin.html

// DBテーブルと1:1になるクラス
@Entity
@Table(name = "user")
data class User(

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name")
    var name: String = "",

    @Column(name = "address")
    var address: String = "",

    @Column(name = "phone")
    var phone: String = "",

    @Column(name = "update_date")
    var updateDate: LocalDateTime = LocalDateTime.MIN,

    @Column(name = "create_date")
    var createDate: LocalDateTime = LocalDateTime.MIN,

    @Column(name = "delete_date")
    var deleteDate: LocalDateTime? = null

)
