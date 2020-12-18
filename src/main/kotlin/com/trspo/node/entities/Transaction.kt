package com.trspo.node.entities

import java.util.*
import com.google.common.hash.Hashing.sha256
import java.nio.charset.StandardCharsets
import com.trspo.grpc.transaction.*

data class Transaction(
        val id:UUID,
        val data: String) {
    val transactionHash:String = hashData()

    private fun hashData(): String {
        val totalData: String = data + id

        return sha256()
                .hashString(totalData, StandardCharsets.UTF_8)
                .toString()
    }

    override fun toString(): String {
        return String.format("Transaction:\nid -> %s\ndata -> %s",id,data);
    }

    public fun fromTransactionRequest(response:TransactionResponse):Transaction{
        val transactionId:UUID = UUID.fromString(response.id)
        return Transaction(transactionId, response.data)
    }

    public fun toTransactionResponse():TransactionResponse{
        return TransactionResponse.newBuilder()
                .setId(id.toString())
                .setData(data)
                .build()
    }
}