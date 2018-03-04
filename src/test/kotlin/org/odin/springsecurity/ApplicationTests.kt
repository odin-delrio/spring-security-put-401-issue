package org.odin.springsecurity

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {

	private val localhostUrl = "http://localhost:8080"

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun getBookIsAccessible() {
		assertThat(
				restTemplate.exchange<Any>(
						"$localhostUrl/v1/books/100",
						HttpMethod.GET,
						HttpEntity.EMPTY,
						String::class
				).statusCode
		).isEqualTo(HttpStatus.OK)
	}

	@Test
	fun putBookIsAccessible() {
		assertThat(
				restTemplate.exchange<Any>(
						"$localhostUrl/v1/books/100",
						HttpMethod.PUT,
						HttpEntity.EMPTY,
						String::class
				).statusCode
		).isEqualTo(HttpStatus.CREATED)
	}
}
