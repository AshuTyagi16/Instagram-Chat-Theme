package com.example.instagramchattheme.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramchattheme.ui.custom.ColorThemeLayoutManager
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.ChatTheme
import com.example.instagramchattheme.util.DataUtils
import com.example.instagramchattheme.util.SpacesItemDecoration
import com.example.instagramchattheme.util.dpToPx

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var rvChat: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var linearLayoutManager: ColorThemeLayoutManager
    private lateinit var spacesItemDecoration: SpacesItemDecoration
    private lateinit var spinnerAdapter: ArrayAdapter<ChatTheme>
    private lateinit var spinner: Spinner
    private lateinit var dataUtils: DataUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        setupToolbar()
        setupRecyclerView()
    }

    private fun inject() {
        dataUtils = DataUtils(this)
        chatAdapter = ChatAdapter()
        linearLayoutManager = ColorThemeLayoutManager(this, RecyclerView.VERTICAL, false)
        spacesItemDecoration = SpacesItemDecoration(50)
        spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, dataUtils.getChatThemes())
    }

    private fun setupToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.app_name)
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.gray)))
        }
    }

    private fun setupRecyclerView() {
        rvChat = findViewById(R.id.rvChat)
        progressBar = findViewById(R.id.progressBar)
        rvChat.apply {
            adapter = chatAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(spacesItemDecoration)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            rvChat.post {
                rvChat.smoothScrollBy(0, 1)
                progressBar.visibility = View.GONE
                rvChat.visibility = View.VISIBLE
                chatAdapter.submitList(dataUtils.getChatList())
            }
        }, 500)
    }

    private fun setupSpinner(menu: Menu) {
        menuInflater.inflate(R.menu.chat_themes, menu)
        val item: MenuItem = menu.findItem(R.id.spinner)
        spinner = item.actionView as Spinner
        spinner.dropDownWidth = 150.dpToPx()
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            setupSpinner(it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        (spinner.selectedItem as ChatTheme).let {
            linearLayoutManager.updateColors(it.startColor, it.endColor)
            rvChat.smoothScrollBy(0, 1)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}