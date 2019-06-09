package com.example.lsuhinin.myapplication.helpers

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.setTransition(TRANSIT_FRAGMENT_OPEN)
    fragmentTransaction.commit()
}