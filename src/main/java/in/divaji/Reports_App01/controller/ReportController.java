package in.divaji.Reports_App01.controller;

import java.io.IOException; 
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import in.divaji.Reports_App01.entity.CitizenPlan;
import in.divaji.Reports_App01.request.SearchRequest;
import in.divaji.Reports_App01.service.ReportService;

@RestController
@RequestMapping("/Report")
public class ReportController {
	
	private Logger logger= LoggerFactory.getLogger(ReportController.class);

	 @Autowired
     private ReportService reportService;
	
	 @PostMapping("/save")
  	public ResponseEntity<String> saveReportData(@RequestBody CitizenPlan citizenPlan){
		 
		ResponseEntity<String> enti=null;	
	  if(citizenPlan!=null) {
		  reportService.saveReportData(citizenPlan); 
		  enti= ResponseEntity.ok("Saved "+citizenPlan.getCitizenId());
	  }else {
		  enti=(ResponseEntity<String>) ResponseEntity.badRequest();
	  }
		return enti;
	}
	 
	 @GetMapping("/getPlaneName")
	 public ResponseEntity<List<String>> getPlaneName(){
		 List<String> planName= reportService.getPlaneName();
		
		return ResponseEntity.ok(planName);	 
	 }
	 @GetMapping("/getPlanStatus")
	 public ResponseEntity<List<String>> getPlaneStatus(){
		 List<String> PlanStatus= reportService.getPlaneStatus();
		return ResponseEntity.ok(PlanStatus);	 
	 }
	 @PostMapping("/getCitizenPlan")
	public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest searchRequest){
		logger.info("Enter into search Method");
		 List<CitizenPlan> citizenPlan= reportService.search(searchRequest);
			/*
			 * model.addAllAttributes(citizenPlan);
			 * 
			 * logger.info("Exit into search Method");
			 */
		 return ResponseEntity.ok(citizenPlan);	 
	 }
	 @GetMapping("/excel")
	 public boolean exportExcel(HttpServletResponse responce)throws Exception{
		
          responce.setContentType("application/octet-stream");
          
			/*
			 * String headerKey="Content-Disposition"; String
			 * headerValue="attechment;filename=citizenPlan.xls";
			 */
		  responce.addHeader("Content-Disposition", "attechment;filename=citizenPlan.xls");
		 
		 reportService.exportExcel(responce);
		 return true;
	 }
	 @GetMapping("/pdf")
	 public boolean pdfExcel(HttpServletResponse responce)throws Exception{
		
          responce.setContentType("application/pdf");
          
			/*
			 * String headerKey="Content-Disposition"; String
			 * headerValue="attechment;filename=citizenPlan.xls";
			 */
		  responce.addHeader("Content-Disposition", "attechment;filename=citizenPlan.pdf");
		 
		 reportService.exportpdf(responce);
		 return true;
	 }
	
	
}
