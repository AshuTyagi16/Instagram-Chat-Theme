package com.example.instagramchattheme.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.ChatTheme
import com.example.instagramchattheme.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_outgoing.view.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var spacesItemDecoration: SpacesItemDecoration
    private lateinit var spinnerAdapter: ArrayAdapter<ChatTheme>
    private lateinit var spinner: Spinner
    private lateinit var dataUtils: DataUtils
    private lateinit var animatedColor: AnimatedColor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        setupToolbar()
        setupRecyclerView()
        setupListeners()
    }

    private fun inject() {
        dataUtils = DataUtils(this)
        chatAdapter = ChatAdapter()
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        spacesItemDecoration = SpacesItemDecoration(50)
        spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, dataUtils.getChatThemes())
        animatedColor = AnimatedColor(
            ContextCompat.getColor(this, R.color.bg_outgoing_start),
            ContextCompat.getColor(this, R.color.bg_outgoing_end)
        )
    }

    private fun setupToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.app_name)
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.gray)))
        }
    }

    private fun setupRecyclerView() {
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

    private fun setupListeners() {
        rvChat.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                recyclerView.forEachVisibleHolder { holder: RecyclerView.ViewHolder ->
                    if (holder is OutgoingChatViewHolder) {
                        changeDrawableColor(holder)
                    }
                }
            }
        })
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
            animatedColor = AnimatedColor(it.startColor, it.endColor)
            rvChat.smoothScrollBy(0, 1)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun changeDrawableColor(holder: OutgoingChatViewHolder) {
        holder.itemView.post {
            ContextCompat.getDrawable(this, R.drawable.bg_round_corner_outgoing)
                ?.let { incomingBgDrawable ->
                    val color = animatedColor.with(getFloatRange(holder.itemView))
                    incomingBgDrawable.updateTint(color)
                    holder.itemView.tvOutgoingMessage.background = incomingBgDrawable
                }
        }
    }

    private fun getFloatRange(view: View): Float {
        return 1f - (view.absY() / resources.displayMetrics.heightPixels.toFloat())
    }
}