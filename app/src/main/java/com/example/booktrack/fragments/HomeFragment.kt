package com.example.booktrack.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.booktrack.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val showPopupButton = view.findViewById<Button>(R.id.add_btn)
//
//        showPopupButton.setOnClickListener {
//            val dialogFragment = AddDialogFragment()
//            dialogFragment.show(childFragmentManager, "AddDialogFragment")
//        }
//    }
//
//    inner class AddDialogFragment : DialogFragment() {
//
//        override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View? {
//            return inflater.inflate(R.layout.popup_menu, container, false)
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//
//            val optionSearchOnline = view.findViewById<TextView>(R.id.optionAddOnline)
//            val optionSearchManual = view.findViewById<TextView>(R.id.optionAddManual)
//
//            optionSearchOnline.setOnClickListener {
//                // Handle search online option
//                dismiss()
//            }
//
//            optionSearchManual.setOnClickListener {
//                // Handle search manual option
//                dismiss()
//            }
//        }
//    }
}
