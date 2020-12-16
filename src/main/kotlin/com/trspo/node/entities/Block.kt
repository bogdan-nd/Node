package com.trspo.node.entities

import java.sql.Timestamp

data class Block(
        val previousHash: String,
        val transactionList: List<Transaction>,
        val accuracy: Int) {

    private val timeStamp: Timestamp = Timestamp(System.currentTimeMillis())
    var nonce:Long =0
    var hash:String =""

    fun getBlockHeader():String{
        var blockHeader : String = previousHash

        for(transaction in transactionList)
            blockHeader += transaction.transactionHash

        return blockHeader + timeStamp
    }

    override fun toString(): String {
        var stringOutput = String.format("Block:\n Hash -> %s\n Previous hash -> %s\n Transactions:\n",hash,previousHash)

        for(transaction in transactionList)
            stringOutput += transaction

        stringOutput += String.format("\n Accuracy -> %s\n Nonce -> %s",accuracy,nonce)
        return stringOutput
    }
}