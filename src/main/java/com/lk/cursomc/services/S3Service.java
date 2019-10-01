package com.lk.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    // Enviar um arquivo local para AWS S3.
    public void uploadFile(String localFilePath) {
        try {
            LOG.info("Iniciando Upload...");

            File file = new File(localFilePath);
            s3Client.putObject(new PutObjectRequest(bucketName, "teste", file));

            LOG.info("Upload Finalizado !");
        }
        catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status Code: " + e.getErrorCode());
        }
        catch (AmazonClientException e) {
            LOG.info("AmazonClientException: " + e.getMessage());
        }

    }


}