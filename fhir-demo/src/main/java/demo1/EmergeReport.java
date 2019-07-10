package demo1;

import java.io.Serializable;
import java.util.Date;

public class EmergeReport implements Serializable{

   private String patientID;
   private String familyName;
   private String givenName;
   private String sex;
   private Date dateOfBirth;
   private String status;
   private ObservationCode code;

   public String getPatientID() {
      return patientID;
   }

   public void setPatientID(String patientID) {
      this.patientID = patientID;
   }

   public String getFamilyName() {
      return familyName;
   }

   public void setFamilyName(String familyName) {
      this.familyName = familyName;
   }

   public String getGivenName() {
      return givenName;
   }

   public void setGivenName(String givenName) {
      this.givenName = givenName;
   }

   public String getSex() {
      return sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public Date getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public ObservationCode getCode() {
      return code;
   }

   public void setCode(ObservationCode code) {
      this.code = code;
   }
}

