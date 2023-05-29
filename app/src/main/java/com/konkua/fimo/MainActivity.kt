package com.konkua.fimo

import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    companion object {
        var url = "http://www.app.monka.media/nowmoney.php"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val myConnection = MyConnection(applicationContext)
//        if (myConnection.registerNetworkCallback())
//            Toast.makeText(applicationContext, "connection", Toast.LENGTH_SHORT).show()
//        else Toast.makeText(applicationContext, "disconnet", Toast.LENGTH_SHORT).show()
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }
        Thread {
            val httpConnection = MyHttpConnection()
            var strRespone = httpConnection.getRespone(url)
        }.start()


        val demoData = arrayListOf(
            "Curabitur sit amet rutrum enim, sit amet commodo urna. Nullam nec nisl eget purus vulputate ultrices nec sit amet est. Sed sodales maximus risus sit amet placerat.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet lectus a mi lobortis iaculis. Mauris odio tortor, accumsan vel gravida sit amet, malesuada a tortor.",
            "Praesent efficitur eleifend eros quis elementum. Vivamus eget nunc ante. Sed sed sodales libero. Nam ipsum lorem, consequat at ipsum sit amet, tempor vulputate nibh.",
            "Aliquam sodales imperdiet augue at consectetur. Suspendisse dui mauris, tincidunt non auctor quis, facilisis et tellus.",
            "Ut non tincidunt neque, et sodales ligula. Quisque interdum in dui sit amet sagittis. Curabitur erat magna, maximus quis libero quis, dapibus eleifend orci."
        )

        viewPager.adapter = CarouselRVAdapter(demoData)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)
    }

    fun toArrayList(jsonString: String): ArrayList<Robocash> {
        val listRo = ArrayList<Robocash>()
        val robocash = Robocash()
        // get JSONObject from JSON file
        val obj = JSONObject(jsonString)
        // fetch JSONObject named employee
        val jsonArray: JSONArray = obj.getJSONArray("CASH")
        (0 until jsonArray.length()).forEach {
            val jsonObject = jsonArray.getJSONObject(it)
            robocash.name = jsonObject.getString("name")
            listRo.add(robocash)
        }

        return listRo
    }
}