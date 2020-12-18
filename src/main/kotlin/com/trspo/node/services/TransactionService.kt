package com.trspo.node.services

import com.trspo.node.entities.Transaction
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Service

@Service
class TransactionService {
    private val url: String = "localhost"
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress(url, 9090)
            .usePlaintext()
            .build()
//    private val stub:TransactionSer
//
//    public fun getTransactions():List<Transaction>{
//
//    }

}