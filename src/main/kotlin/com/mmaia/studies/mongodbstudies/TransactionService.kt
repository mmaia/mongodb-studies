package com.mmaia.studies.mongodbstudies

import com.mmaia.studies.mongodbstudies.generator.TransactionDocumentGenerator
import com.mmaia.studies.mongodbstudies.repository.TransactionRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import kotlinx.coroutines.*


@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val transactionDocumentGenerator: TransactionDocumentGenerator
) {

    @EventListener(ApplicationReadyEvent::class)
    fun createAndSave() {
        println("Creating and saving documents")
        GlobalScope.launch {
            while (true) {
                val transaction = TransactionDocumentGenerator.gen(transactionDocumentGenerator)
                transactionRepository.save(transaction)
            }
        }
        println("exit fun createAndSave....")
    }
}