package com.example.schedulerdelay.task

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "task")
class TaskEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var date: LocalDateTime? = null,

    var changed: Boolean = false,
) {

    fun addSeconds(times: Long) {
        date = date?.plusSeconds(times)
    }


    fun change() {
        changed = true
    }
}