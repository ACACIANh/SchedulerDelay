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
class SleepTaskService(
    val sleepTaskRepository: SleepTaskRepository,
) {
    @PostConstruct
    fun init() {
        val maxCount: Int = 10
        val intervalTime: Long = 10
        var timeHolder = LocalDateTime.now()

        for (i: Int in 1..maxCount) {
            timeHolder = timeHolder.plusSeconds(intervalTime)
            val sleepTaskEntity = SleepTaskEntity(null, timeHolder)
            sleepTaskRepository.save(sleepTaskEntity)
        }
    }

    fun doWork() {

        val now = LocalDateTime.now()
        val sleepTaskEntities = sleepTaskRepository.findAllByDateIsBefore(now)
        log.info { "entity count : ${sleepTaskEntities.count()}" }

        sleepTaskEntities.forEach {
            log.info { "id : ${it.id} refresh time" }
            it.addSeconds(60)
            it.change()
        }

        this.sleep()
    }

    fun sleep() {
        val sleepTimeSeconds = 10L
        log.info { "sleep task service sleep start - localTime : ${LocalTime.now()}" }
        Thread.sleep(sleepTimeSeconds * 1000)
        log.info { "sleep task service sleep ended - localTime : ${LocalTime.now()}" }
    }
}