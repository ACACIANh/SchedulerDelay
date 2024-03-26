package com.example.schedulerdelay

import com.example.schedulerdelay.task.SleepTaskService
import com.example.schedulerdelay.task.TaskService
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalTime

const val EVERY_SECOND = "* * * * * *"
const val CUSTOM_SECOND = "0/3 * * * * *"

private val log = KotlinLogging.logger { }

@Service
class SchedulerService(
    val sleepTaskService: SleepTaskService,
    val taskService: TaskService,
) {

    @Async
    @Scheduled(cron = CUSTOM_SECOND)
    fun sleepTaskSchedule() {

        log.info { "sleep task scheduler service - localTime : ${LocalTime.now()}" }
        sleepTaskService.doWork()
    }

//    @Async
//    @Scheduled(cron = EVERY_SECOND)
//    fun taskSchedule() {
//
//        log.info { "task scheduler service - localTime : ${LocalTime.now()}" }
//        taskService.doWork()
//    }
}