package com.example.schedulerdelay.task

import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime

private val log = KotlinLogging.logger { }

@Service
@Transactional
class TaskService(
    val taskRepository: TaskRepository,
) {
    @PostConstruct
    fun init() {
        val maxCount: Int = 10
        val intervalTime: Long = 10
        var timeHolder = LocalDateTime.now()

        for (i: Int in 1..maxCount) {
            timeHolder = timeHolder.plusSeconds(intervalTime)
            val taskEntity = TaskEntity(null, timeHolder)
            taskRepository.save(taskEntity)
        }
    }

    fun doWork() {

        val now = LocalDateTime.now()
        val taskEntities = taskRepository.findAllByDateIsBefore(now)
        log.info { "entity count : ${taskEntities.count()}" }

        taskEntities.forEach {
            log.info { "id : ${it.id} refresh time" }
            it.addSeconds(60)
            it.change()
        }
    }
}