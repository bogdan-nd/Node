package com.trspo.node.entities

import java.util.*
import com.google.common.hash.Hashing.sha256
import java.nio.charset.StandardCharsets

data class Transaction(
        val data: String) {
    private val id: UUID = UUID.randomUUID()
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
}