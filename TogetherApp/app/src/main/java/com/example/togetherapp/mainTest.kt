package com.example.togetherapp

object mainTest {
    private val existingEmails = listOf("sanduni@gmail.com")

    fun validateSignUpInput(
        name: String,
        email: String,
        password: String
    ): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false
        }
        if (email in existingEmails) {
            return false
        }
        if (password.count { it.isDigit() } < 5) {
            return false
        }
        return true
    }
}