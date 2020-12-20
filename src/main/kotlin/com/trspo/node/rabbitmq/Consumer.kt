package com.trspo.node.rabbitmq

import com.trspo.node.entities.Block
import com.trspo.node.services.PoW
import com.trspo.node.services.TransactionService
import com.trspo.node.services.ValidationService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Consumer {
    @Autowired
    lateinit var powService: PoW

    @Autowired
    lateinit var transactionService: TransactionService

    @Autowired
    lateinit var validationService: ValidationService


    @RabbitListener(queues = ["\${rabbitmq.queue}"])
    fun validateBlock(block: Block) {

    }
}