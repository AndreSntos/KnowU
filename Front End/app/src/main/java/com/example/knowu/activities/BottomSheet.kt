package com.example.knowu.activities

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.core.view.get
import com.example.knowu.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.PEEK_HEIGHT_AUTO
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheet : BottomSheetDialogFragment() {

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        //Must add any view here, actual view will be inflated in onCreateView

        dialog.setContentView(R.layout.bottom_sheet_profile)
        //Dialog wraps view in frame layout
        val root: View = dialog.findViewById(R.id.design_bottom_sheet)
        //Now we have access to BottomSheetBehavior
        val behavior = BottomSheetBehavior.from(root)

        dialog.findViewById<TextView>(R.id.tvUsuario).text = "GUIIIIII"
        //Do whatever you need:
        behavior.setPeekHeight(500, true);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        return dialog
    }


    fun teste() {

    }
}