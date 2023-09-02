package com.devilkim.app.name

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NameController() {

    @PostMapping("/name")
    fun create(@RequestBody nameRequestDto: NameRequestDto): BaseResponseDto<String> {
        return BaseResponseDto.ok("test")
    }
}