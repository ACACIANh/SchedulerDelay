package com.example.schedulerdelay

import com.example.schedulerdelay.task.SleepTaskService
import com.example.schedulerdelay.task.TaskService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalTime

const val EVERY_SECOND = "* * * * * *"
const val CUSTOM_SECOND = "0/10 * * * * *"

private val log = KotlinLogging.logger { }

@Service
class SchedulerService(
    val sleepTaskService: SleepTaskService,
    val taskService: TaskService,
) {

    @Scheduled(cron = EVERY_SECOND)
    fun sleepTaskSchedule() {

        log.info { "sleep task scheduler service - localTime : ${LocalTime.now()}" }
        sleepTaskService.doWork()
    }

    @Scheduled(cron = EVERY_SECOND)
    fun taskSchedule() {

        log.info { "task scheduler service - localTime : ${LocalTime.now()}" }
        taskService.doWork()
    }
}