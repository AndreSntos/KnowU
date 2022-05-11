package com.example.knowu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.knowu.ContaFragment
import com.example.knowu.PerfilContaFragment

class PerfilContaAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return PerfilContaFragment()
            1 -> return ContaFragment()
            else -> return PerfilContaFragment()
        }
    }

}