package uz.zazu.king.questionnaire.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "employee_questionnaire")
public class EmployeeQuestionnaire {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private boolean isActive;

    private String branch;                   
    private String fullName;                
    private String phoneNumber;             
    private String position;                
    private String status;                  
    private String maritalStatus;           
    private Double salary;                  
    private Double ltv;                     
    private Integer satisfactionLevel;      
    private String motivationPreferences;   
    private String ideasToEaseWork;         
    private String thingsYouTolerate;       
    private String orgImprovements;         
    private String mostLikedAboutWork;      
    private String dreamJob;                
    private String nearTwoYearsPlans;       
    private String inFiveYears;             
    private String reasonWorkingWithUs;     
    private Integer rating;                 
    private String comments;                
    private String managerComments;
}