package com.example.demoandroid.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.demoandroid.R
import com.example.demoandroid.data.model.RelatedTopic
import com.example.demoandroid.viewmodel.CharViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharDetailActivity : AppCompatActivity() {

    private val userViewModel by viewModels<CharViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.item_view_detail)

        val topic: RelatedTopic = intent.extras!!.get("obj") as RelatedTopic
        userViewModel.select(topic)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                    R.id.detailContainer,
                    CharDetailViewFragment()
            ).commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}