package com.hgsc.fhir.utils;

import java.io.*;
import java.util.HashMap;

public class FileUtils {

   public static String PROJECT_DIRECTORY = "/Users/feiy/IdeaProjects/fhir-implementation/hgsc-fhir-client/";

   public HashMap<String, String> readMapperConfig(String fileName) {
      HashMap<String, String> mappingConfig = new HashMap<>();
      File myFile = new File(fileName);
      if (!myFile.exists()) {
         System.err.println("File Not Found:" + fileName);
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
         e.getStackTrace();
      }
      return mappingConfig;
   }

   public byte[] readBytesFromFile(String fileName) {
      File file = new File(fileName);
      byte[] bytesArray = null;

      try (FileInputStream fileInputStream = new FileInputStream(file)) {
         bytesArray = new byte[(int)file.length()];
         fileInputStream.read(bytesArray);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return bytesArray;
   }
}