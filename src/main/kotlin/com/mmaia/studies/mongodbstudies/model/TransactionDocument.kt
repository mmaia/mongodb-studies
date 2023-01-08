package com.mmaia.studies.mongodbstudies.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.HashIndexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("transactions")
data class TransactionDocument(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @HashIndexed
    val guid: String,
    val originId: String,
    val executedAt: Long,
    val type: TransactionType,
    val priceMoney: Money?,
    val sentMoney: Money?,
    val receivedMoney: Money?,
    val feesMoney: Money?,
    val address: String?,
)
