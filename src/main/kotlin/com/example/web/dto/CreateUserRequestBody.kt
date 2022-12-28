package com.example.web.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateUserRequestBody(

    @NotEmpty(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100文字以内で入力してください")
    val name: String = "",

    @Size(max = 255, message = "住所は255文字以内で入力してください")
    @NotEmpty(message = "住所を入力してください")
    val address: String = "",

    @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
    val phone: String = ""

)
