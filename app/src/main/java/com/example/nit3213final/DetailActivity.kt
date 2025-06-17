package com.example.nit3213final

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {

    //private lateinit var scrollDetails: ScrollView // Uncomment if needed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Bottom back arrow click
        val backArrowBottom = findViewById<ImageView>(R.id.imgBackArrowBottom)
        backArrowBottom.setOnClickListener {
            finish()
        }

        // Logout button
        val logoutBtn = findViewById<MaterialButton>(R.id.btnLogout)
        logoutBtn.setOnClickListener {
            goToLogin()
        }

        // Scroll-to-top and dashboard buttons
        // scrollDetails = findViewById(R.id.scrollDetails)
        // findViewById<ImageView>(R.id.imgDetailLogoBottom).setOnClickListener {
        //     scrollDetails.post { scrollDetails.fullScroll(ScrollView.FOCUS_UP) }
        // }
        findViewById<ImageView>(R.id.imgDashboardLogoBottom).setOnClickListener {
            Intent(this, DashboardActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .also { startActivity(it) }
            finish()
        }

        // Populate details from intent extras
        val container = findViewById<LinearLayout>(R.id.containerDetails)
        val fields = intent.getSerializableExtra("fields") as? Map<String, String> ?: emptyMap()
        fields.forEach { (key, value) ->
            val label = "${key.replaceFirstChar { it.uppercase() }}: "
            val spannable = SpannableString(label + value).apply {
                setSpan(
                    StyleSpan(Typeface.BOLD),
                    0, label.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            TextView(this).apply {
                text = spannable
                textSize = 16f
                setPadding(0, 8, 0, 8)
            }.also { container.addView(it) }
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
