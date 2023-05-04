package in.divaji.Reports_App01.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import in.divaji.Reports_App01.entity.CitizenPlan;

@Component
public class ExcelGenerator {

  public void generate(HttpServletResponse responc, List<CitizenPlan> citizenPlans,File f) throws Exception {
	HSSFWorkbook workbook=new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet();
    HSSFRow row=  sheet.createRow(0);
    
    row.createCell(0).setCellValue("ID");
    row.createCell(1).setCellValue("citizenName");
    row.createCell(2).setCellValue("gender");
    row.createCell(3).setCellValue("planeName");
    row.createCell(4).setCellValue("planeStatus");
    row.createCell(5).setCellValue("planStartDate");
    row.createCell(6).setCellValue("planEndDate");
    row.createCell(7).setCellValue("benefitAmount");
    row.createCell(8).setCellValue("denialReason");
    row.createCell(9).setCellValue("terminatedDate");
    row.createCell(10).setCellValue("terminationReason");
    
    int rowCount=1;
    
    for(CitizenPlan cpt:citizenPlans) {
    	
    	HSSFRow dataRow=sheet.createRow(rowCount);
    	dataRow.createCell(0).setCellValue(cpt.getCitizenId());
    	dataRow.createCell(1).setCellValue(cpt.getCitizenName());
    	dataRow.createCell(2).setCellValue(cpt.getGender());
    	dataRow.createCell(3).setCellValue(cpt.getPlaneName());
    	dataRow.createCell(4).setCellValue(cpt.getPlaneStatus());
    	dataRow.createCell(5).setCellValue(cpt.getPlanStartDate()+"");
    	dataRow.createCell(6).setCellValue(cpt.getPlanEndDate()+"");
    	if(null !=cpt.getBenefitAmount()) {
    		dataRow.createCell(7).setCellValue(cpt.getBenefitAmount());	
    	}else {
    		dataRow.createCell(7).setCellValue("N/A");
    	}
    	if(null !=cpt.getDenialReason()) {
    		dataRow.createCell(8).setCellValue(cpt.getDenialReason());
    	}else {
    		dataRow.createCell(8).setCellValue("N/A");
    	}
    	if(null !=cpt.getTerminatedDate()) {
    		dataRow.createCell(9).setCellValue(cpt.getTerminatedDate()+"");
    	}else {
    		dataRow.createCell(9).setCellValue("N/A");
    	}
    	if(null !=cpt.getTerminationReason()) {
    		dataRow.createCell(10).setCellValue(cpt.getTerminationReason());
    	}else {
    		dataRow.createCell(10).setCellValue("N/A");
    	}
    	
    	rowCount++;
    }
         
          FileOutputStream file= new FileOutputStream(f);
          workbook.write(file); 
        
            ServletOutputStream ops= responc.getOutputStream();
             workbook.write(ops);
           
            workbook.close();
	}

}
