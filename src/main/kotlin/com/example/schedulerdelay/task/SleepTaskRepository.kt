package com.example.schedulerdelay.task

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface SleepTaskRepository : JpaRepository<SleepTaskEntity, Long> {

    fun findAllByDateIsBefore(date: LocalDateTime): List<SleepTaskEntity>
}