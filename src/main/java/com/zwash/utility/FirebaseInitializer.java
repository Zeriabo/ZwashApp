package com.zwash.utility;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.FileInputStream;
import java.io.IOException;
public class FirebaseInitializer {



    public static void initializeFirebaseApp() {
        try {
       
            Dotenv dotenv = Dotenv.configure().load();
            
            // Decrypt the JSON file content
            String decryptedContent = decryptJsonFile();

      
            // Create Firebase options from the decrypted content
            FirebaseOptions options = new FirebaseOptions.Builder()
            		.setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(decryptedContent.getBytes())))
                    .build();

            FirebaseApp.initializeApp(options);
            
         	encryptJsonFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String decryptJsonFile() throws IOException {
        FileInputStream file = new FileInputStream("config/encrypted-zwash-385807-firebase-adminsdk-3fuy0-71a94dfd15.json");

        // Read encrypted file content
        byte[] encryptedBytes = file.readAllBytes();
        String encryptedContent = new String(encryptedBytes);

        // Decrypt the JSON file content using EncryptionUtils
        String decryptedContent = EncryptionUtils.decrypt(encryptedContent);
        

        file.close();
        return decryptedContent;
    }
    private static void encryptJsonFile() throws IOException {
    	File file= new File("config/encrypted-zwash-385807-firebase-adminsdk-3fuy0-71a94dfd15.json");
        FileInputStream fileinputStream = new FileInputStream(file);

        // Read the JSON file content
        byte[] jsonBytes = fileinputStream.readAllBytes();
        String jsonContent = new String(jsonBytes);

        // Encrypt the JSON file content using EncryptionUtils
        String encryptedContent = EncryptionUtils.encrypt(jsonContent);

        // Write the encrypted content back to the file
        FileOutputStream outputFile = new FileOutputStream("config/encrypted-zwash-385807-firebase-adminsdk-3fuy0-71a94dfd15.json");
        outputFile.write(encryptedContent.getBytes());
 
       
        outputFile.close();
    }

}
