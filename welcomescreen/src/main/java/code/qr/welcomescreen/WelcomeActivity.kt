package code.qr.welcomescreen

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import code.qr.welcomescreen.activities.BaseActivityNoToolbar
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivityNoToolbar() {
    override var lay: Int = R.layout.activity_welcome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = WelcomePagerAdapter()
        welcome_pager.adapter = adapter
        welcome_pager.setPageTransformer(true, WelcomeViewPagerTransformer())
        welcome_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    2 -> {
                        log_btn.visibility = View.VISIBLE
                        log_btn.setOnClickListener {
                            startActivity(Intent(context, LoginActivity::class.java))
                            finish()
                        }
                    }
                    else -> {
                        log_btn.visibility = View.GONE
                        log_btn.setOnClickListener { null }
                    }
                }
            }
        })

    }


}
