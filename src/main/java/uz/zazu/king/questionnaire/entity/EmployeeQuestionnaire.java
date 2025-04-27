package uz.zazu.king.questionnaire.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_questionnaires")
public class EmployeeQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "branch")
    private String branch;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "position")
    private String position;

    @Column(name = "status")
    private String status;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "ltv")
    private Double ltv;

    @Column(name = "satisfaction_level")
    private Integer satisfactionLevel;

    @Column(name = "motivation_preferences")
    private String motivationPreferences;

    @Column(name = "ideas_to_ease_work")
    private String ideasToEaseWork;

    @Column(name = "things_you_tolerate")
    private String thingsYouTolerate;

    @Column(name = "org_improvements")
    private String orgImprovements;

    @Column(name = "most_liked_about_work")
    private String mostLikedAboutWork;

    @Column(name = "dream_job")
    private String dreamJob;

    @Column(name = "near_two_years_plans")
    private String nearTwoYearsPlans;

    @Column(name = "in_five_years")
    private String inFiveYears;

    @Column(name = "reason_working_with_us")
    private String reasonWorkingWithUs;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "manager_comments")
    private String managerComments;
}