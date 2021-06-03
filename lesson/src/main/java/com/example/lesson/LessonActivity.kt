package com.example.lesson

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.core.BaseView
import com.example.lesson.entity.Lesson

class LessonActivity: AppCompatActivity(), BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {
    var lessonPresenter: LessonPresenter = LessonPresenter(this)

    var lessonAdapter: LessonAdapter = LessonAdapter()

    lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        var recycleView: RecyclerView = findViewById(R.id.list)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = lessonAdapter
        recycleView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        refreshLayout = findViewById(R.id.swipe_refresh_layout)
//        refreshLayout.setOnRefreshListener { SwipeRefreshLayout.OnRefreshListener {
//            Log.e("WillWolf", "onRefresh-->")
//            getPresenter().fetchData()
//            }
//        }
        refreshLayout.setOnRefreshListener {
            Log.e("WillWolf", "onRefresh-->")
            getPresenter().fetchData()
        }
        refreshLayout.isRefreshing = true
        getPresenter().fetchData()
    }

    fun showResult(lesson: List<Lesson>) {
        Log.e("WillWolf", "showResult")
        lessonAdapter.updateAndNotify(lesson)
        refreshLayout.isRefreshing = false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        getPresenter().showPlayback()
        return false
    }

    override fun getPresenter(): LessonPresenter {
        return lessonPresenter
    }
}