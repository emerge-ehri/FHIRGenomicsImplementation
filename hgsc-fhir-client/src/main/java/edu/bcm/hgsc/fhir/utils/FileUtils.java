package edu.bcm.hgsc.fhir.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class FileUtils {

   private static Logger logger = Logger.getLogger(FileUtils.class);

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

//   public byte[] getS3ObjectAsByteArray(String key){
//
//      logger.info("Downloading %s from S3 bucket %s...\n", key, s3bucket);
//
//      final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//
//      byte[] s3ObjectBytes = null;
//      S3ObjectInputStream s3is = null;
//      try {
//         S3Object o = s3.getObject(s3bucket, key);
//         s3is = o.getObjectContent();
//         s3ObjectBytes = generateByteArray(s3is);
//      } catch (AmazonServiceException e) {
//         logger.error("GetS3ObjectAsByteArray Failed:" + e.getMessage());
//      } finally {
//         try {
//            if (s3is!=null) s3is.close();
//         } catch (Exception e2){
//            logger.error("S3ObjectInputStream Closing Failed:" + e2.getMessage());
//         }
//      }
//      return s3ObjectBytes;
//   }

//   private byte[] generateByteArray(InputStream is) {
//
//      byte[] pdfBytes = null;
//      try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
//         byte[] buffer = new byte[1024];
//         int len;
//         while ((len = is.read(buffer)) != -1) {
//            bos.write(buffer, 0, len);
//         }
//         pdfBytes = bos.toByteArray();
//      } catch (Exception e) {
//         logger.error("ConvertPdftoBase64String Failed:" + e.getMessage());
//      }
//
//      return pdfBytes;
//   }
}
