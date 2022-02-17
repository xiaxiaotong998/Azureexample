package com.xia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import java.io.*;
import java.util.UUID;

public class Application {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Azure-example;user=sa;password=Xiaxt3536.";
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=testexample;AccountKey=gtjq2b6hqIiIDNlwPXYowDWraSfJ7v7eVPuveDKKzaHzmOmDr/ivdRYHx/ikF1h64bTtC5e/GgTW+AStRGUm1A==;EndpointSuffix=core.windows.net";
        ResultSet resultSet = null;
        try {
            try (Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement()) {

                String selectSql = "SELECT name from [User]";
                resultSet = statement.executeQuery(selectSql);

                while (resultSet.next()) {
                    System.out.println("Hello Azure service "+ resultSet.getString(1));
                }


                BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
                String containerName = "testexampleblobs2e6b5347-37eb-4ce4-9f9a-46e1c445426d";
                BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);


//                final String filePath = "/Users/xiaxiaotong/Desktop/screenshot-2.png";
////                BlobClient blobClient = containerClient.getBlobClient("screenshot-2.png");
////                File source = new File(filePath);
////		        blobClient.upload(new FileInputStream(source), source.length());
///

                for (BlobItem blobItem : containerClient.listBlobs()) {
                    System.out.println("Blob name: " + blobItem.getName() + ", Snapshot: " + blobItem.getSnapshot());
                }

            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}
