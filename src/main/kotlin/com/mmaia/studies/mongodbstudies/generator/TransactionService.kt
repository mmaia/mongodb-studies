package com.mmaia.studies.mongodbstudies.generator

import com.mmaia.studies.mongodbstudies.model.TransactionDocument
import kotlinx.coroutines.*
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service


@Service
class TransactionService(
    val mongoTemplate: MongoTemplate,
    val transactionDocumentGenerator: TransactionDocumentGenerator
) {
    @EventListener(ApplicationReadyEvent::class)
    fun createAndSave() {
        println("Creating and saving documents")
        GlobalScope.launch {
            while (true) {
                val transactionList = mutableListOf<TransactionDocument>()
                mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, TransactionDocument::class.java)
                    .let { bulkOperations ->
                        repeat(1_000) {
                            transactionList.add(TransactionDocumentGenerator.gen(transactionDocumentGenerator))
                        }
                        bulkOperations.insert(transactionList)
                        bulkOperations.execute()
                    }
            }
        }
        println("exit fun createAndSave....")
    }
}