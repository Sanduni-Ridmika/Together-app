import android.content.Intent
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.togetherapp.LogIn
import com.example.togetherapp.R
import com.example.togetherapp.UserProfile
import com.google.firebase.auth.FirebaseAuth

open class BaseActivity : AppCompatActivity() {
    private lateinit var hamburgerButton: ImageButton

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        hamburgerButton = findViewById(R.id.hamburgerButton)
        hamburgerButton.setOnClickListener { showPopupMenu() }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(this, hamburgerButton)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_profile -> {
                    startActivity(Intent(this, UserProfile::class.java))
                    true
                }
                R.id.menu_logout -> {
                    // Perform logout action
                    performLogout()
                    true
                }
                else -> false
            }
        }
        popupMenu.menuInflater.inflate(R.menu.hamburger_menu, popupMenu.menu)
        popupMenu.show()
    }
    // Function to perform the logout action
    private fun performLogout() {
        // Log out the user
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
    }
}
