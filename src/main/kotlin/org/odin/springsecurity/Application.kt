package org.odin.springsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class IssueApplication

fun main(args: Array<String>) {
    runApplication<IssueApplication>(*args)
}

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
                .antMatchers("/v1/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .httpBasic()
    }
}

data class Book(val id: String)

@RestController
@RequestMapping("/v1")
class BooksController {

    @GetMapping("/books/{id}")
    fun getBook(@PathVariable("id") id: String): Book {
        return Book(id)
    }

    @PutMapping("/books/{id}")
    fun putBook(@PathVariable("id") id: String): ResponseEntity<Unit> {
        System.out.println("book $id saved...")
        return status(HttpStatus.CREATED).build<Unit>()
    }
}
