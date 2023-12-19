package com.example.aplikasibelajar.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.aplikasibelajar.R
import com.example.aplikasibelajar.adapter.AdapterPage
import com.example.aplikasibelajar.utils.SessionManager
import kotlinx.android.synthetic.main.activity_walktrought.*

class Walktrought : AppCompatActivity(), View.OnClickListener {

    private lateinit var mPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView>
    private lateinit var mAdapter: AdapterPage
    private var layouts: IntArray = intArrayOf(
        R.layout.walktrought_first,
        R.layout.walktrought_second,
        R.layout.walktrought_third,
        R.layout.walktrought_fourth
    )
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walktrought)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        session = SessionManager(applicationContext)
        btnNext.setOnClickListener(this)
        btnSkip.setOnClickListener(this)
        mPager = findViewById(R.id.pager)
        mAdapter = AdapterPage(layouts, this)
        mPager.adapter = mAdapter
        dotsLayout = findViewById(R.id.dots)
        createDots(0)
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                createDots(p0)
                if (p0 == layouts.size - 1) {
                    btnNext?.text = getString(R.string.start)
                    btnSkip.visibility = View.INVISIBLE
                } else {
                    btnNext?.text = getString(R.string.next)
                    btnSkip.visibility = View.VISIBLE
                }

            }

        })
        //Pengecekan Session
        isFirstime()

    }

    private fun moveActivity() {
        session.putIsInGame(false)
        val intent = Intent(applicationContext, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isFirstime() {
        val isFirstime = session.getValueBoolean("is_firstime")
        if (isFirstime!!) {
            moveActivity()
        }
    }

    fun createDots(position: Int) {
        dotsLayout.removeAllViews()

        dots = Array(layouts.size) { ImageView(this) }

        for (i in layouts.indices) {

            dots[i] = ImageView(this)

            if (i == position) {
                dots[i].setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.active_dots
                    )
                )
            } else {
                dots[i].setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.inactive_dots
                    )
                )
            }

            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(4, 0, 4, 0)
            dotsLayout.addView(dots[i], params)


        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnNext -> {
                if (btnNext?.text!! == "Next") {
                    mPager.currentItem = mPager.currentItem + 1
                } else {
                    session.putBoolean("is_firstime", true)
                    moveActivity()
                }

            }
            R.id.btnSkip -> {
                session.putBoolean("is_firstime", true)
                moveActivity()
            }
        }
    }
}