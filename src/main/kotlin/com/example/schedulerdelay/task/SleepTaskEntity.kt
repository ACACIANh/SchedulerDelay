package com.example.schedulerdelay.task

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "sleep_task")
class SleepTaskEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var date: LocalDateTime? = null,

    var changed: Boolean = false,
) {

    fun addSeconds(times: Long) {
        date = LocalDateTime.now().plusSeconds(times)
    }

    fun change() {
        changed = true
    }
}