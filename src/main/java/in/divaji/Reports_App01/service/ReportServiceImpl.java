package in.divaji.Reports_App01.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.divaji.Reports_App01.controller.ReportController;
import in.divaji.Reports_App01.entity.CitizenPlan;
import in.divaji.Reports_App01.repo.CitizenRepository;
import in.divaji.Reports_App01.request.SearchRequest;
import in.divaji.Reports_App01.util.Emailutils;
import in.divaji.Reports_App01.util.ExcelGenerator;
import in.divaji.Reports_App01.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService{
	
	private Logger logger= LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private PdfGenerator generator;
	 @Autowired
	private Emailutils emailutils;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
      @Override
    public void saveReportData(CitizenPlan citizenPlan) {
      citizenRepository.save(citizenPlan);	   
    }

	@Override
	public List<String> getPlaneName() {
		List<String> planName=   citizenRepository.getPlaneName();
		return planName;
	}

	@Override
	public List<String> getPlaneStatus() {
		List<String> planstatus= citizenRepository.getplaneStatus();
		return planstatus;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest searchRequest) {
		logger.info("Enter into search Method");
		
		//SearchRequest searchRequest= new SearchRequest();
		
		CitizenPlan citizenPlan=new CitizenPlan();
		
		if(null !=searchRequest.getPlaneName() && !"".equals(searchRequest.getPlaneName())){
			
			citizenPlan.setPlaneName(searchRequest.getPlaneName());	
		  String re=	searchRequest.getPlaneName();
			System.out.println(re);
		}
       if(null !=searchRequest.getPlaneStatus() && !"".equals(searchRequest.getPlaneStatus())){
    	   String re=	searchRequest.getPlaneStatus();
			System.out.println(re);
			citizenPlan.setPlaneStatus(searchRequest.getPlaneStatus());	
		}
       if(null !=searchRequest.getGender() && !"".equals(searchRequest.getGender())){
    	   String re=	searchRequest.getGender();
			System.out.println(re);
			citizenPlan.setGender(searchRequest.getGender());	
		}	
       logger.info("Exit into search Method");
		
		return citizenRepository.findAll(Example.of(citizenPlan));
	}

	@Override
	public boolean exportpdf(HttpServletResponse responce) throws Exception, Exception {
		
		File file= new File("citizenPlan.pdf");
        List<CitizenPlan>citizenPlans=citizenRepository.findAll();
        
        generator.pdfGenerator(responce, citizenPlans,file);
          String subject="Citizen Plane Data";
	     String body="<h1>Citizen Plane Data<h1>";
	     String to="jessijessi973@gmail.com";
	     
	     
	     emailutils.sendEmail(subject, body, to, file);
	     file.delete();
		return true;
	}

	@Override
	public boolean exportExcel(HttpServletResponse responce) throws Exception  {
		
		File file= new File("citizenPlan.xls");
      
       List<CitizenPlan>citizenPlans=citizenRepository.findAll();
       
	     excelGenerator.generate(responce, citizenPlans,file);
	     
	     String subject="Citizen Plane Data";
	     String body="<h1>Citizen Plane Data<h1>";
	     String to="jessijessi973@gmail.com";
	     
	     
	     emailutils.sendEmail(subject, body, to, file);
	     
	     file.delete();
	 
		return  true;
	}

}
