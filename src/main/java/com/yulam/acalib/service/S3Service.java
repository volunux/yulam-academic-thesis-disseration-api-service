package com.yulam.acalib.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

import static com.yulam.acalib.util.DateTimeUtil.getTimeInMillis;

@Component
public class S3Service {

  @Autowired
  private AmazonS3 amazonS3;

  public String generateSignedUrl(String bucketName, String fileName, HttpMethod httpMethod) {
    return generateSignedUrl(bucketName, fileName, httpMethod, 1);
  }

  public String generateSignedUrl(String bucketName, String fileName, HttpMethod httpMethod, int hour) {
    Calendar expirationTime = Calendar.getInstance();
    expirationTime.setTime(new Date());
    expirationTime.add(Calendar.HOUR, hour);
    URL url = amazonS3.generatePresignedUrl(bucketName, fileName, expirationTime.getTime(), httpMethod);
    return url.toString();
  }

  public String generateSignedUrl(String bucketName, String fileName, HttpMethod httpMethod, Date expirationDate) {
    if (Objects.isNull(expirationDate)) {
      expirationDate = new Date();
      long expirationTimeInMillis = expirationDate.getTime();
      expirationTimeInMillis += getTimeInMillis(60, 60, 24, 7);
      expirationDate.setTime(expirationTimeInMillis);
    }

    GeneratePresignedUrlRequest preSignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
    preSignedUrlRequest.withExpiration(expirationDate);
    preSignedUrlRequest.withMethod(httpMethod);

    URL url = amazonS3.generatePresignedUrl(preSignedUrlRequest);
    return url.toString();
  }

  public String getObjectSignedUrl(String bucketName, String fileName) {
    if (isObjectExists(bucketName, fileName)) {
      throw new RuntimeException("File does not exists");
    }
    return generateSignedUrl(bucketName, fileName, HttpMethod.GET);
  }

  public String generateObjectSignedUrl(String bucketName, String extension) {
    String fileName = UUID.randomUUID().toString() + extension;
    return generateSignedUrl(bucketName, fileName, HttpMethod.PUT);
  }

  public String getObjectKeyFromUrl(@NotNull String objectUrl) {
    return objectUrl.substring(objectUrl.lastIndexOf("/") + 1);
  }

  public String deleteObject(@NotNull String bucketName, @NotNull String fileName) {
    if (isObjectExists(bucketName, fileName)) {
      throw new RuntimeException("File does not exists");
    }

    DeleteObjectRequest objectRequest = new DeleteObjectRequest(bucketName, fileName);
    amazonS3.deleteObject(objectRequest);

    return "Successfully deleted";
  }

  public void deleteMultipleObjects(String bucketName, @NotNull List<String> objects){
    DeleteObjectsRequest delObjectsRequests = new DeleteObjectsRequest(bucketName)
            .withKeys(objects.toArray(new String[0]));
    amazonS3.deleteObjects(delObjectsRequests);
  }

  public void moveObject(String bucketSourceName, String objectName, String bucketTargetName) {
    amazonS3.copyObject(
            bucketSourceName, objectName,
            bucketTargetName, objectName);
  }

  private boolean isObjectExists(String bucketName, String fileName) {
    return !amazonS3.doesObjectExist(bucketName, fileName);
  }

  private String generateObjectKey(String objectName) {
    return (new Date()).getTime() +
            "-" +
            objectName.replace(" ", "_");
  }

  public Object getObjectStream(String fileName) {
    S3ObjectInputStream result = amazonS3.getObject("bucket", fileName).getObjectContent();
    return ResponseEntity
            .ok()
            .cacheControl(CacheControl.noCache())
            .header("Content-type", MediaType.APPLICATION_OCTET_STREAM_VALUE)
            .header("Content-disposition", "attachment; filename=".concat(fileName))
            .body(new InputStreamResource(result));
  }

  @Async
  public void uploadObject(String bucketName, MultipartFile multipartFile, Optional<Map<String, String>> objectMetaData) {
    try {
      File file = convertMultipartFileToFile(multipartFile);
      String fileName = LocalDateTime.now().toString().concat("_").concat(file.getName());

      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
      putObjectRequest.setMetadata(new ObjectMetadata());
      putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetaData.ifPresent(map -> {
        if (!map.isEmpty()) {
          map.forEach(objectMetadata::addUserMetadata);
        }
      });

      amazonS3.putObject(putObjectRequest);
      Files.delete(file.getAbsoluteFile().toPath());
    }
    catch (Exception e) {
      throw new RuntimeException("Error processing file upload");
    }
  }

  private File convertMultipartFileToFile(final MultipartFile multipartFile) {
    String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
    final File file = new File(fileName);

    try (final FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(multipartFile.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return file;
  }

}
