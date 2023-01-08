package com.mmaia.studies.mongodbstudies.repository

import com.mmaia.studies.mongodbstudies.model.TransactionDocument
import org.springframework.data.mongodb.repository.MongoRepository

public interface TransactionRepository: MongoRepository<TransactionDocument, String>