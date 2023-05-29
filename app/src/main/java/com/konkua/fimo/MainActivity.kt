package com.konkua.fimo

import com.konkua.fimo.CarouselRVAdapter
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    val listRo = ArrayList<Robocash>()
     var roboList= ArrayList<Robocash>()
    var myAdapter= CarouselRVAdapter(roboList)

    companion object {
        var url = "http://www.app.monka.media/fimo.php"
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


        viewPager.adapter = myAdapter

        Thread {
            val httpConnection = MyHttpConnection()
            val strRespone = httpConnection.getRespone(url)
            toArrayList(strRespone)

//            myAdapter = CarouselRVAdapter(roboList)
//            viewPager.adapter = CarouselRVAdapter(listRo)
        }.start()
        myAdapter.notifyDataSetChanged()

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("click======>", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                // useless
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // useless too
            }
        })
    }

    fun toArrayList(jsonString: String): ArrayList<Robocash> {


        // get JSONObject from JSON file
        val obj = JSONObject(jsonString)
        // fetch JSONObject named employee
        val jsonArray: JSONArray = obj.getJSONArray("FIMO")
        (0 until jsonArray.length()).forEach {
            val jsonObject = jsonArray.getJSONObject(it)
            val robocash = Robocash()
            robocash.name = jsonObject.getString("NAME")
            Log.d("name======>", robocash.name)
            robocash.affiliate = jsonObject.getString("AFFILIATELINK")
            robocash.iconLink = jsonObject.getString("NAME")
            robocash.titleFirst = jsonObject.getString("TITTLE1")
            robocash.msgFirst = jsonObject.getString("MSG1")
            robocash.titleSecond = jsonObject.getString("TITTLE2")
            robocash.msgSecond = jsonObject.getString("MSG2")
            robocash.titleThird = jsonObject.getString("TITTLE3")
            robocash.msgThird = jsonObject.getString("MSG3")
//            listRo.add(robocash)
            roboList.add(robocash)
        }

        return roboList
    }
}