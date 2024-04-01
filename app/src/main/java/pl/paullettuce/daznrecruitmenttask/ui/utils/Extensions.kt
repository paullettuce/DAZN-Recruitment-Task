package pl.paullettuce.daznrecruitmenttask.ui.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.paullettuce.daznrecruitmenttask.R

fun Context.toast(@StringRes textRes: Int): Toast =
    Toast.makeText(this, textRes, Toast.LENGTH_SHORT)

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.animateShow() {
    val popInAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_up)
    startAnimation(popInAnimation)
    show()
}

fun View.animateHide() {
    val popOutAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_down)
    popOutAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {}

        override fun onAnimationEnd(animation: Animation?) {
            hide()
        }

        override fun onAnimationRepeat(animation: Animation?) {}
    })
    startAnimation(popOutAnimation)
}


fun RecyclerView.addDefaultDivider() = addItemDecoration(
    DividerItemDecoration(
        context,
        (layoutManager as LinearLayoutManager).orientation
    )
)

fun RecyclerView.scrollPosition() =
    (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

fun RecyclerView.addOnScrollUpListener(onScrollUp: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy < 0) { // on scroll up
                onScrollUp()
            }
        }
    })
}