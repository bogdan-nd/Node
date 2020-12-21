package com.trspo.node.services

import com.trspo.node.entities.Block
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class MinedBlockProducer {
    @Autowired
    lateinit var transactionService: TransactionService
    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate
    @Value("\${rabbitmq.block-exchange}")
    lateinit var blockExchange: String


    fun sendMinedBlockToNetwork(minedBlock: Block) {
        rabbitTemplate.convertAndSend(blockExchange, "", minedBlock)
        print("\nSent block to the network\n")
        markPoolTransactionsMined(minedBlock)
    }

    fun markPoolTransactionsMined(minedBlock: Block){
        val transactions = minedBlock.transactionList
        transactionService.markTransactionMined(transactions)
    }
}