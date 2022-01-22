package br.com.rocketseat.nextlevelweek.plantmanager

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Context.hideSoftKeyboard(view: View) {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideSoftKeyboard() {
    view?.let { activity?.hideSoftKeyboard(it) }
}

fun AppCompatActivity.hideBackButtonToBar() {
    supportActionBar?.let { actionBar ->
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.setHomeButtonEnabled(false)
    }
}