package com.mmaia.studies.mongodbstudies.generator

import com.mmaia.studies.mongodbstudies.model.TransactionDocument
import com.mmaia.studies.mongodbstudies.repository.TransactionRepository
import kotlinx.coroutines.*
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service


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
                val transactionList = mutableListOf<TransactionDocument>()
                for (i in 1..1000) {
                    transactionList.add(TransactionDocumentGenerator.gen(transactionDocumentGenerator))
                }
                withContext(Dispatchers.IO) {
                    transactionRepository.saveAll(transactionList)
                }
            }
        }
        println("exit fun createAndSave....")
    }
}