package com.trspo.node.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class SpringAsyncConfig : AsyncConfigurer {

    @Bean
    override fun getAsyncExecutor(): Executor {
        return ThreadPoolTaskExecutor()
    }
}