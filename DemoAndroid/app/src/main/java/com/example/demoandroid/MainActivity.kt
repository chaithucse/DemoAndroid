package com.example.demoandroid

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demoandroid.common.AppUtils
import com.example.demoandroid.data.model.RelatedTopic
import com.example.demoandroid.databinding.ActivityMainBinding
import com.example.demoandroid.view.CharDetailActivity
import com.example.demoandroid.view.CharListFragment
import com.example.demoandroid.view.CharDetailViewFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Launcher activity of the application
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CharListFragment.ItemListListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Check the network condition and show dialog if it is not connected
         */
        if(AppUtils.isNetworkConnected(this)) {
            showNetworkErrorDialog()
            return
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.list_container, CharListFragment())
                .commit()
        }

        if (this.resources.getBoolean(R.bool.isTablet)) {
            supportFragmentManager.beginTransaction().replace(
                R.id.detailContainer,
                CharDetailViewFragment()
            ).commit()
        }
    }

    override fun itemClicked(topic: RelatedTopic) {
        if (findViewById<FrameLayout>(R.id.detailContainer) == null) {
            // start detail activity
            val detailIntent = Intent(this, CharDetailActivity::class.java)
            detailIntent.putExtra("obj", topic)
            startActivity(detailIntent)
        } else {
            supportFragmentManager.beginTransaction().replace(
                R.id.detailContainer,
                CharDetailViewFragment()
            ).addToBackStack("tag").commit()
        }
    }

    /**
     * Dialog display to the users if there is no network. close the application on button tap.
     */
    private fun showNetworkErrorDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
        builder.setTitle(resources.getString(R.string.error_dialog_title))
        builder.setMessage(resources.getString(R.string.network_error))
        builder.setNegativeButton(android.R.string.ok) { dialog, which ->
            dialog.cancel()
            finish()
        }
        builder.show()
    }
}