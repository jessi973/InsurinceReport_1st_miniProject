package in.divaji.Reports_App01.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {

	private String planeName;
	private String planeStatus;
	private String gender;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	
}
