package com.cebrailyilmaz.todoapplication.ui.update

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.cebrailyilmaz.todoapplication.R


class StyleCallBack constructor(val editContent: EditText) : android.view.ActionMode.Callback {
    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.text_style_actions, menu)
        mode?.menu?.removeItem(android.R.id.selectAll)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        var cs: CharacterStyle
        val start: Int = editContent.getSelectionStart()
        val end: Int = editContent.getSelectionEnd()
        val ssb = SpannableStringBuilder(editContent.text)

        return when (item?.itemId) {
            R.id.ts_bold -> {
                cs = StyleSpan(Typeface.BOLD)
                ssb.setSpan(cs, start, end, 1)
                editContent.setText(ssb)
                true
            }
            R.id.ts_italic -> {
                cs = StyleSpan(Typeface.ITALIC)
                ssb.setSpan(cs, start, end, 1)
                editContent.text = ssb
                true
            }
            R.id.ts_underline -> {
                cs = UnderlineSpan()
                ssb.setSpan(cs, start, end, 1)
                editContent.text = ssb
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {

    }

}