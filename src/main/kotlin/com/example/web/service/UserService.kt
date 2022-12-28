package com.example.web.service

import com.example.web.dto.CreateUserRequestBody
import com.example.web.dto.SearchUserRequestBody
import com.example.web.dto.UpdateUserRequestBody
import com.example.web.entity.User
import com.example.web.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

// https://qiita.com/YU-TA-9/items/2ede8e95744584000173

@Service
@Transactional
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun searchAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: Long): User {
        return userRepository.findById(id).get()
    }

    fun create(createUserRequestBody: CreateUserRequestBody) {

        val now = LocalDateTime.now()
        val user = User()
        user.name = createUserRequestBody.name
        user.address = createUserRequestBody.address
        user.phone = createUserRequestBody.phone
        user.createDate = now
        user.updateDate = now
        userRepository.save(user)

    }

    fun update(updateUserRequestBody: UpdateUserRequestBody) {

        val now = LocalDateTime.now()
        val user = findById(updateUserRequestBody.id)
        user.name = updateUserRequestBody.name
        user.address = updateUserRequestBody.address
        user.phone = updateUserRequestBody.phone
        user.updateDate = now
        userRepository.save(user)

    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun findByName(searchUserRequestBody: SearchUserRequestBody): List<User> {

        val name = searchUserRequestBody.name
        return userRepository.findByNameIgnoreCaseContaining(name)

    }

}
