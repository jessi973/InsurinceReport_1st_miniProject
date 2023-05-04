package in.divaji.Reports_App01.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CitizenPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String citizenName;
	private String gender;
	private String planeName;
	private String planeStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
    private Double benefitAmount;
    private String denialReason;
    private LocalDate terminatedDate;
    private String terminationReason;
}
