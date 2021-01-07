package com.cebrailyilmaz.todoapplication.ui.update


import android.widget.EditText
import android.widget.Toast
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.ui.SharedViewModel
import java.time.LocalDate
import java.util.logging.Level
import java.util.logging.Logger

class UpdateFragmentHandlers {
    val logger = Logger.getLogger(javaClass.name)


    private fun onShareButtonClicked() {}

    fun onSaveButtonClicked(editContent: EditText, viewModel: SharedViewModel) {
        if (viewModel.currentTodo.value?.id != -1L) {
            val oldTodo = viewModel.currentTodo.value!!
            val updatedTodo = oldTodo.copy(
                    title = editContent.text.toString().split("\\\n")[0],
                    content = editContent.text.toString(),
                    dateLastModified = LocalDate.now().toEpochDay()
            )
            viewModel.updateTodo(updatedTodo)
        } else {
            val cTodo = Todo(
                    0,
                    editContent.text.toString().split("\\\n")[0],
                    editContent.text.toString(),
                    LocalDate.now().toEpochDay(),
                    LocalDate.now().toEpochDay()
            )
            viewModel.createTodo(cTodo)
        }

        //Handle Keyboard!!!!
        //hideKeyboard()
        logger.log(Level.SEVERE, "heheh")
        Toast.makeText(editContent.context, "Save clicked", Toast.LENGTH_SHORT).show()
    }
}