package in.divaji.Reports_App01.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.divaji.Reports_App01.entity.CitizenPlan;
import in.divaji.Reports_App01.request.SearchRequest;

public interface ReportService { 
	
	
	public void saveReportData(CitizenPlan citizenPlan);
	
	public List<String> getPlaneName();
	
	public List<String> getPlaneStatus();
	
	public List<CitizenPlan>  search(SearchRequest searchRequest);
	
	public boolean exportExcel(HttpServletResponse responce)throws Exception;
	
	public boolean exportpdf(HttpServletResponse responce)throws Exception, Exception;

}
