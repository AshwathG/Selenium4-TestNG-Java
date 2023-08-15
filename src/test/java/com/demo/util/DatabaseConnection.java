package com.demo.util;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {
	
	public static String GetOTP(String mobileNumber)
	{
		String otpNumber = null;
		
		// Replace the placeholder with your Atlas connection string
        String uri = ConfigReader.getInstance().getMongoDBConnectionString();
        
        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
        		.version(ServerApiVersion.V1)
        		.build();
        
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .serverApi(serverApi)
                .build();
        
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("db_user");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
            }
            MongoCollection<Document> collection = database.getCollection("customerotps");
            
            Document query = new Document("mobile", mobileNumber).append("status", "Inactive");
            
            // Perform a find operation with the query
            FindIterable<Document> documents = collection.find(query);
            
            for (Document document : documents) {
            	otpNumber = document.getString("otpNumber");
            }
            
            // Close the connection when done
            mongoClient.close();
        }
        return otpNumber;
	}

}
