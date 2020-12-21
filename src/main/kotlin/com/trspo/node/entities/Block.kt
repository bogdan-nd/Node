package com.trspo.node.entities

import lombok.NoArgsConstructor
import java.sql.Timestamp

@NoArgsConstructor
data class Block(
        val previousHash: String,
        val transactionList: List<Transaction>,
        val accuracy: Int) {
    constructor() : this("", ArrayList<Transaction>(), 6)

    var nonce: Long = 0
    lateinit var timeStamp: Timestamp
    lateinit var hash: String
    lateinit var minerId: String

    fun getBlockHeader(): String {
        var blockHeader: String = previousHash

        for (transaction in transactionList)
            blockHeader += transaction.transactionHash

        return blockHeader
    }

    fun finishMining() {
        timeStamp = Timestamp(System.currentTimeMillis())
    }
}