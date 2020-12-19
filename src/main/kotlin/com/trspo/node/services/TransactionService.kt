package com.trspo.node.services

import com.google.protobuf.Empty
import com.trspo.grpc.transaction.TransactionBatchRequest
import com.trspo.grpc.transaction.TransactionBatchResponse
import com.trspo.grpc.transaction.TransactionServiceGrpc
import com.trspo.grpc.transaction.TransactionServiceGrpc.TransactionServiceBlockingStub
import com.trspo.node.entities.Transaction
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
class TransactionService {
    private val url: String = "localhost"
    private val channel: ManagedChannel = ManagedChannelBuilder.forAddress(url, 9090)
            .usePlaintext()
            .build()
    private val stub: TransactionServiceBlockingStub = TransactionServiceGrpc.newBlockingStub(channel)

    fun getTransactions(): List<Transaction> {
        val emptyRequest: Empty = Empty.newBuilder().build()
        val transactionBatch: TransactionBatchResponse = stub.getTransactions(emptyRequest)

        return Transaction.fromTransactionBatch(transactionBatch)
    }

    fun returnToPull(transactions: List<Transaction>) {
        val returnRequest: TransactionBatchRequest = Transaction.toReturnRequest(transactions)

        stub.returnTransactionsResponse(returnRequest)
    }

}