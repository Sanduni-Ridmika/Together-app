package com.example.togetherapp

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class mainTestTest {
    @Test
    fun `empty email`() {
        val result = mainTest.validateSignUpInput(
            "Sanduni",
            "",
            "12345"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `email already exist`() {
        val result = mainTest.validateSignUpInput(
            "Sanduni",
            "sanduni@gmail.com",
            "12345"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password is not strong enough`() {
        val result = mainTest.validateSignUpInput(
            "Sanduni",
            "sandunii@gmail.com",
            "12"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `valid credentials`() {
        val result = mainTest.validateSignUpInput(
            "Sanduni",
            "sandunii@gmail.com",
            "12345"
        )
        assertThat(result).isTrue()
    }

}