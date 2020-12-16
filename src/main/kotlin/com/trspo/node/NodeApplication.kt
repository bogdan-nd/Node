package com.trspo.node

import com.trspo.node.entities.Block
import com.trspo.node.entities.Transaction
import com.trspo.node.services.PoW
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigInteger
import java.sql.Time

//@SpringBootApplication
class NodeApplication

fun main(args: Array<String>) {
//    runApplication<NodeApplication>(*args)
    val list: List<Transaction> = listOf()
    val abc = Block("st", list, 3)
    val algoritm = PoW()
    val startTime = System.currentTimeMillis()
    var result = algoritm.proofOfWork(abc)
    val endTime = System.currentTimeMillis()
    print("\n" + (endTime - startTime) / 1000 + "\n")
    print(abc)

}
