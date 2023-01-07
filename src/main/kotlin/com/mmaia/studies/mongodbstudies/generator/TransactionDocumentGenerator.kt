package com.mmaia.studies.mongodbstudies.generator

import com.mmaia.studies.mongodbstudies.model.Money
import com.mmaia.studies.mongodbstudies.model.TransactionDocument
import com.mmaia.studies.mongodbstudies.model.TransactionType
import kotlin.random.Random

class TransactionDocumentGenerator {
    companion object {
        fun gen(tdg: TransactionDocumentGenerator): TransactionDocument {
            return TransactionDocument(
                guid = "guid",
                originId = "originId",
                executedAt = 0,
                type = tdg.randomTransactionType(),
                priceMoney = Money(tdg.randomDouble(), "USD"),
                sentMoney = Money(tdg.randomDouble(), "USD"),
                receivedMoney = Money(tdg.randomDouble(), "USD"),
                feesMoney = Money(tdg.randomDouble(), "USD"),
                address = "address",
            )
        }
    }

    // create function to randomically pick value from TransactionType enum
    fun randomTransactionType(): TransactionType {
        return TransactionType.values().random()
    }

    // create function to randomically pick double value between 0.1 and 100
    fun randomDouble(): Double {
        return Random.nextDouble(0.1, 1000.0)
    }
}