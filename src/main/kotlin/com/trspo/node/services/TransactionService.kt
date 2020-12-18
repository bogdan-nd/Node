package com.trspo.node.services

import com.google.protobuf.Empty
import com.trspo.grpc.transaction.*
import com.trspo.grpc.transaction.TransactionServiceGrpc.TransactionServiceBlockingStub
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
    private val stub: TransactionServiceBlockingStub = TransactionServiceGrpc.newBlockingStub(channel)

    public fun getTransactions(): MutableList<TransactionMessage> {
        val emptyRequest:Empty = Empty.newBuilder().build()
        val transactionBatch:TransactionBatchResponse = stub.getTransactions(emptyRequest)
        return transactionBatch.transactionsList
    }

    public fun returnToPull(transactions: List<Transaction>){
        var transactionMessageList:MutableList<TransactionMessage> = ArrayList()
        var transactionMessage:TransactionMessage

        for(transaction in transactions){
            transactionMessage = transaction.toTransactionMessage()
            transactionMessageList.add(transactionMessage)
        }

        val returnRequest = ReturnTransactionsRequest.newBuilder()
                .addAllTransactions(transactionMessageList)
                .build()

        stub.returnTransactionsResponse(returnRequest)
    }

}