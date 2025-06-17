package com.example.nit3213final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DashboardAdapter
    private val itemList = mutableListOf<DashboardItem>()

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar = findViewById<Toolbar>(R.id.toolbarDashboard)
        setSupportActionBar(toolbar)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val username = intent.getStringExtra("username") ?: "User"
        tvWelcome.text = getString(R.string.welcome_fmt, username)

        val logoutBtn = findViewById<MaterialButton>(R.id.btnLogout)
        logoutBtn.setOnClickListener {
            goToLogin()
        }

        recyclerView = findViewById(R.id.recyclerViewItems)
        adapter = DashboardAdapter(itemList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        findViewById<ImageView>(R.id.imgScrollTop).setOnClickListener {
            recyclerView.smoothScrollToPosition(0)
        }

        // Observe ViewModel LiveData
        viewModel.items.observe(this, Observer { items ->
            itemList.clear()
            itemList.addAll(items)
            adapter.notifyDataSetChanged()
        })

        viewModel.errorMessage.observe(this, Observer { msg ->
            msg?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.fetchDashboardItems(
            keypass = intent.getStringExtra("keypass") ?: "",
            username = username,
            password = intent.getStringExtra("password") ?: ""
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                goToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
