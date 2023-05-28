package com.example.togetherapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogInTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var firebaseUser: FirebaseUser

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        ActivityScenario.launch(LogIn::class.java).onActivity { activity ->
            activity.mAuth = firebaseAuth
        }
    }

    @Test
    fun emptyEmail() {
        val result = login("", "password123")
        assertFalse(result)
    }

    @Test
    fun emptyPassword() {
        val result = login("test@example.com", "")
        assertFalse(result)
    }

    @Test
    fun validEmailAndPassword() {
        `when`(firebaseAuth.signInWithEmailAndPassword("sanduni28@gmail.com", "sanduni12345")).thenReturn(null)
        val result = login("sanduni28@gmail.com", "sanduni12345")
        assertTrue(result)
    }

    @Test
    fun incorrectEmailFormat() {
        val result = login("testexample.com", "password123")
        assertFalse(result)
    }

    @Test
    fun nonExistingUser() {
        `when`(firebaseAuth.signInWithEmailAndPassword("nonexisting@example.com", "password123")).thenReturn(null)
        val result = login("nonexisting@example.com", "password123")
        assertFalse(result)
    }

    @Test
    fun incorrectPassword() {
        `when`(firebaseAuth.signInWithEmailAndPassword("test@example.com", "wrongpassword")).thenReturn(null)
        val result = login("test@example.com", "wrongpassword")
        assertFalse(result)
    }

    private fun login(email: String, password: String): Boolean {
        // Perform the login operation using the provided email and password
        if (email.isEmpty() || password.isEmpty()) {
            return false
        }

        var loginResult = false
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loginResult = true
                } else {
                    loginResult = false
                }
            }
        Thread.sleep(2000)

        return loginResult
    }

}
