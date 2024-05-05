package com.example.project.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "NutriScan"; // Replace with your actual database name
    }

    @Override
    public MongoClient mongoClient() {
        // Construct connection settings
        String connectionString = "mongodb+srv://Uzi:NutriScan1@nutriscan.nyzvgbd.mongodb.net/?retryWrites=true&w=majority&appName=NUtriScan";
        ConnectionString connString = new ConnectionString(connectionString);

        // Configure server API
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        // Create MongoClient settings
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .serverApi(serverApi)
                .build();

        // Create and return the MongoClient
        return MongoClients.create(settings);
    }
}
