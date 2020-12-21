package com.trspo.node.services

import com.trspo.node.entities.Block
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Future

@Service
@RestController
@RequestMapping("abc")
class MinerService {
    @Autowired
    lateinit var powService: PoW

    @Autowired
    lateinit var transactionService: TransactionService

    @Autowired
    lateinit var validationService: ValidationService

    @Autowired
    lateinit var minedBlockProducer: MinedBlockProducer

    @Value("\${node-id}")
    lateinit var nodeId: String

    var previousHash: String = "a"
    lateinit var blockBeingMined: Future<Block>

    fun startMiningStage(a: String) {
        print("\nGet message for starting mining\n")
        val transAmount = (3..7).random()
        val transactions = transactionService.getTransactions(transAmount)
        val block = Block(previousHash, transactions, 6)
        blockBeingMined = powService.proofOfWork(block)
    }

    fun validateBlock(block: Block) {
        val sameNode: Boolean = nodeId == block.minerId
        if (sameNode)
            return

        print(String.format("\nGet block for validation, mined by %s\n", block.minerId))

        val blockCorrectness = validationService.validate(block)
        if (!blockCorrectness)
            return

        if (!blockBeingMined.isDone)
            blockBeingMined.cancel(true)

        print(String.format("\nBlock, mined by %s, is correct", block.minerId))
        previousHash = block.hash
    }
}