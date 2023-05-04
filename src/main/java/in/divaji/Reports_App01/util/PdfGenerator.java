package in.divaji.Reports_App01.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.divaji.Reports_App01.entity.CitizenPlan;

@Component
public class PdfGenerator {

	
	public void pdfGenerator(HttpServletResponse responce,List<CitizenPlan>citizenPlans, File f) throws Exception, Exception {
	Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, responce.getOutputStream());
    PdfWriter.getInstance(document, new FileOutputStream(f));
     
    document.open();
    
    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    font.setSize(20);
  
    
     
    Paragraph p = new Paragraph("citizenPlan", font);
    p.setAlignment(Paragraph.ALIGN_CENTER);   
    document.add(p);
    
    
     
    PdfPTable table = new PdfPTable(11);
    table.setSpacingBefore(9);

    table.addCell("ID");
    table.addCell("citizen   Name");
    table.addCell("gender");
    table.addCell("plane  Name");
    table.addCell("plane   Status");
    table.addCell("plan    StartDate");
    table.addCell("plan   EndDate");
    table.addCell("benefit   Amount");
    table.addCell("denial   Reason");
    table.addCell("terminated   Date");
    table.addCell("termination  Reason");

    
    for(CitizenPlan citizenPlan:citizenPlans) {
    	table.addCell(citizenPlan.getCitizenId()+"");
        table.addCell(citizenPlan.getCitizenName());
        table.addCell(citizenPlan.getGender());
        table.addCell(citizenPlan.getPlaneName());
        table.addCell(citizenPlan.getPlaneStatus());
        table.addCell(citizenPlan.getPlanStartDate()+"");
        table.addCell(citizenPlan.getPlanEndDate()+"");
        table.addCell(citizenPlan.getBenefitAmount()+"");
        table.addCell(citizenPlan.getDenialReason());
        table.addCell(citizenPlan.getTerminatedDate()+"");
        table.addCell(citizenPlan.getTerminationReason());
    	
    }

     
    document.add(table);
     
    document.close();
	}	
}
