package com.zephysus.mindnest.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zephysus.mindnest.core.model.data.User
import kotlinx.datetime.Instant

@Entity(
    tableName = "user",
)
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val email: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)


fun UserEntity?.toDomain(): User? {
    return this?.let {
        User(
            id = id,
            name = name,
            email = email,
            createdAt = createdAt,
        )
    }
}

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    email = email,
    createdAt = createdAt,
)