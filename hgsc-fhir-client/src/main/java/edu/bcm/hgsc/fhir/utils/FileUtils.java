package edu.bcm.hgsc.fhir.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class FileUtils {

   private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

   final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

   public HashMap<String, String> readMapperConfig(String fileName) {
      HashMap<String, String> mappingConfig = new HashMap<>();
      File myFile = new File(fileName);
      if (!myFile.exists()) {
         logger.error("File Not Found:" + fileName);
      }
      try (FileInputStream fis = new FileInputStream(fileName);
           InputStreamReader inputStreamReader = new InputStreamReader(fis, "UTF-8");
           BufferedReader in  = new BufferedReader(inputStreamReader)) {

         String str;
         while ((str = in.readLine()) != null) {
            String[] arr = str.split(":");
            mappingConfig.put(arr[0], arr[1]);
         }
      } catch (IOException e) {
         logger.error("readMapperConfig Failed:", e);
      }
      return mappingConfig;
   }

   public byte[] readBytesFromFile(File file) {
      byte[] bytesArray = null;

      try (FileInputStream fileInputStream = new FileInputStream(file)) {
         bytesArray = new byte[(int)file.length()];
         fileInputStream.read(bytesArray);
      } catch (IOException e) {
         logger.error("readBytesFromFile Failed:", e);
      }
      return bytesArray;
   }

   public String loadPropertyValue(String propertiesFileName, String propertyName) {

      try (InputStream input = new FileInputStream(getClass().getClassLoader().getResource(propertiesFileName).getPath())) {
         Properties prop = new Properties();
         prop.load(input);
         return prop.getProperty(propertyName);
      } catch (IOException e) {
         logger.error("loadPropertyValue Failed:", e);
      }

      return null;
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
