package com.mmaia.studies.mongodbstudies.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig: AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String = "transactions"

    override fun mongoClient(): MongoClient =
        ConnectionString("mongodb://root:rootpassword@localhost:27017/?retryWrites=true&w=1&j=false")
            .let { connectionString ->
                MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build()
            }.let { settings ->
                MongoClients.create(settings)
            }

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), databaseName)
    }
}