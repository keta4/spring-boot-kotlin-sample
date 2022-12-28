package com.example.web.repository

import com.example.web.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByNameIgnoreCaseContaining(name: String): List<User>

}
