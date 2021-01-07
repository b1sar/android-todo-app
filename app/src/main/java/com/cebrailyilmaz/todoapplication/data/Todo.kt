package com.cebrailyilmaz.todoapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todos")
@Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val title: String,
    val content: String,
    val dateCreated: Long,
    val dateLastModified: Long
) : Parcelable

data class Todo2(
        var id: Long,
        val author: String,//not sure if this is needed
        val title: String,
        val content: String,
        val dateCreated: Long,
        val dateLastModified: Long,
        val locked: Boolean,
        val lockPassword: String,
        val priority: TodoPriority,
        val deleted: Boolean,
        val deletionDate: Long = -1,
        val archived: Boolean,
        val archivingDate: Long = -1
        //alarm date?
)

sealed class TodoPriority() {
    class High: TodoPriority()
    class Low: TodoPriority()
    class Medium: TodoPriority()
}