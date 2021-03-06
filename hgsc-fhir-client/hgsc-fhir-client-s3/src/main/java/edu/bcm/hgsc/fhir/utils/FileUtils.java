package edu.bcm.hgsc.fhir.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class FileUtils {

   private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

   final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

   public HashMap<String, String> readMapperConfig(String fileName) {

      HashMap<String, String> mappingConfig = new HashMap<>();

      try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
           InputStreamReader isr = new InputStreamReader(is, "UTF-8");
           BufferedReader br = new BufferedReader(isr)) {

         String str;
         while ((str = br.readLine()) != null) {
            String[] arr = str.split(":");
            mappingConfig.put(arr[0], arr[1]);
         }
      } catch (IOException e) {
         logger.error("readMapperConfig Failed:", e);
      }

      return mappingConfig;
   }

   public String loadPropertyValue(String propertiesFileName, String propertyName) {

      try (InputStream input = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
         Properties prop = new Properties();
         prop.load(input);
         return prop.getProperty(propertyName);
      } catch (Exception e) {
         logger.error("loadPropertyValue Failed:", e);
      }

      return null;
   }

   public ArrayList<String> getJSONFileListFromS3(String orgName) {

      ArrayList<String> fileList = new ArrayList<String>();
      String s3Bucket = loadPropertyValue("application.properties", "s3.bucketname");

      ListObjectsRequest req = new ListObjectsRequest().withBucketName(s3Bucket).withPrefix(orgName);
      ObjectListing result;

      try {
         do {
            result = s3.listObjects(req);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
               if(objectSummary.getKey().endsWith(".json")) {
                  fileList.add(objectSummary.getKey());
                  logger.info("Find object " + objectSummary.getKey() + " which size is " + objectSummary.getSize());
               }
            }
         } while (result.isTruncated());
      } catch (Exception e) {
         logger.error("GetJSONFileListFromS3 Failed:" + e.getMessage());
      }

      return fileList;
   }

   public HashMap<String, String> getFileNameMapFromS3(String orgName, String fileType) {

      HashMap<String, String> fileMap = new HashMap<String, String>();
      String s3Bucket = loadPropertyValue("application.properties", "s3.bucketname");

      ListObjectsRequest req = new ListObjectsRequest().withBucketName(s3Bucket).withPrefix(orgName);
      ObjectListing result;

      try {
         do {
            result = s3.listObjects(req);
            for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
               String objectKey = objectSummary.getKey();
               if(objectKey.endsWith(fileType) && fileType.equals(".txt")) {
                  String[] arr = objectKey.split("_");
                  String[] tempKeyArr = arr[0].split("/");
                  String mapKey = tempKeyArr[tempKeyArr.length - 1];
                  fileMap.put(mapKey, objectKey);
               }else if(objectKey.endsWith(fileType) && fileType.equals(".pdf")) {
                  String mapKey = objectKey.split("\\.")[1];
                  fileMap.put(mapKey, objectKey);
               }else{
                  continue;
               }
               logger.info("Find object " + objectKey + " which size is " + objectSummary.getSize());
            }
         } while (result.isTruncated());
      } catch (Exception e) {
         logger.error("GetFileListFromS3 Failed:" + e.getMessage());
      }

      return fileMap;
   }

   public byte[] getS3ObjectAsByteArray(String key){

      String s3bucket = loadPropertyValue("application.properties", "s3.bucketname");

      logger.info("Getting " + key + " from S3 bucket: " + s3bucket);

      byte[] s3ObjectBytes = null;
      S3ObjectInputStream s3is = null;
      try {
         S3Object o = s3.getObject(s3bucket, key);
         s3is = o.getObjectContent();
         s3ObjectBytes = generateByteArray(s3is);
      } catch (AmazonServiceException e) {
         logger.error("GetS3ObjectAsByteArray Failed:" + e.getMessage());
      } finally {
         try {
            if (s3is!=null) s3is.close();
         } catch (Exception e2){
            logger.error("S3ObjectInputStream Closing Failed:" + e2.getMessage());
         }
      }
      return s3ObjectBytes;
   }

   public byte[] generateByteArray(InputStream is) {

      byte[] bytes = null;
      try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
         byte[] buffer = new byte[1024];
         int len;
         while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
         }
         bytes = bos.toByteArray();
      } catch (Exception e) {
         logger.error("ConvertPdftoBase64String Failed:" + e.getMessage());
      }

      return bytes;
   }
}
