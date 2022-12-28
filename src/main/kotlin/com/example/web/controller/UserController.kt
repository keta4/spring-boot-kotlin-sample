package com.example.web.controller

import com.example.web.dto.CreateUserRequestBody
import com.example.web.dto.SearchUserRequestBody
import com.example.web.dto.UpdateUserRequestBody
import com.example.web.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun displayUsers(model: Model): String {

        val userList = userService.searchAll()
        model.addAttribute("userList", userList)
        model.addAttribute("searchUserRequestBody", SearchUserRequestBody())
        return "/users/list"

    }

    @GetMapping("/users/{id}")
    fun displayUserDetails(
        @PathVariable id: Long,
        model: Model
    ): String {

        val user = userService.findById(id)
        model.addAttribute("userData", user)
        return "/users/details"

    }

    @GetMapping("/users/add")
    fun displayAdd(model: Model): String {

        model.addAttribute("createUserRequestBody", CreateUserRequestBody())
        return "/users/add"

    }

    @PostMapping("/users")
    fun createUser(
        @Validated createUserRequestBody: CreateUserRequestBody,
        result: BindingResult,
        model: Model
    ): String {

        // 入力チェックエラーの場合
        if (result.hasErrors()) {
            return "/users/add"
        }

        // ユーザー新規登録
        userService.create(createUserRequestBody)
        return "redirect:/users"

    }

    @GetMapping("/users/{id}/edit")
    fun displayEdit(
        @PathVariable id: Long,
        model: Model
    ): String {

        val user = userService.findById(id)
        val updateUserRequestBody = UpdateUserRequestBody()
        updateUserRequestBody.id = user.id
        updateUserRequestBody.name = user.name
        updateUserRequestBody.address = user.address
        updateUserRequestBody.phone = user.phone
        model.addAttribute("updateUserRequestBody", updateUserRequestBody)
        return "/users/edit"

    }

    @PostMapping("/users/{id}")
    fun updateUser(
        @Validated updateUserRequestBody: UpdateUserRequestBody,
        result: BindingResult,
        model: Model
    ): String {

        // 入力チェックエラーの場合
        if (result.hasErrors()) {
            return "/users/add"
        }

        // ユーザー編集
        userService.update(updateUserRequestBody)
        return "redirect:/users/%d".format(updateUserRequestBody.id)

    }

    @GetMapping("/users/{id}/delete")
    fun deleteUser(
        @PathVariable id: Long,
        model: Model
    ): String {

        userService.delete(id)
        return "redirect:/users"

    }

    @PostMapping("/users/search")
    fun displaySearchUsers(
        searchUserRequestBody: SearchUserRequestBody,
        model: Model
    ): String {

        val userList = userService.findByName(searchUserRequestBody)
        model.addAttribute("userList", userList)
        return "/users/list"

    }
}
