package com.trspo.node.services

import com.google.common.hash.Hashing
import com.trspo.node.entities.Block
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class PoWService() {
    fun proofOfWork(block:Block){
        val blockHeader = block.getBlockHeader()
        val target:String = stringMultiply("0",block.accuracy)
        var nonce:Long = 0;
        var hash = getHash(blockHeader+nonce)

        while( !hash.startsWith(target)) {
            nonce += 1
            val potentialOutput = blockHeader + nonce
            hash = getHash(potentialOutput)
        }

        block.nonce = nonce
        block.hash = hash
    }

    private fun getHash(text:String):String{
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }

    private fun stringMultiply(text:String,times:Int):String{
        return text.repeat(times-1)
    }
}