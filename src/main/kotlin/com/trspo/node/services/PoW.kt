package com.trspo.node.services

import com.google.common.hash.Hashing
import com.trspo.node.entities.Block
import java.nio.charset.StandardCharsets

class PoW() {
    fun proofOfWork(block:Block){
        val blockHeader = block.getBlockHeader()
        val target:String = "0".repeat(block.accuracy)
        var nonce:Long = 0;
        var hash = getHash(blockHeader+nonce)

        while(hash.startsWith(target)) {
            val potentialOutput = blockHeader + nonce
            hash = getHash(potentialOutput)
            nonce += 1
        }

        block.nonce = nonce
        block.hash = hash
    }

    private fun getHash(text:String):String{
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }
}