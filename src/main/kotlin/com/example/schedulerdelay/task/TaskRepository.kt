package com.example.schedulerdelay.task

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface TaskRepository : JpaRepository<TaskEntity, Long> {

    fun findAllByDateIsBefore(date: LocalDateTime): List<TaskEntity>
}