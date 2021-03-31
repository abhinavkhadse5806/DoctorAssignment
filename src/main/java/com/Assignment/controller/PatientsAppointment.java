package com.Assignment.controller;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Assignment.domain.Appointments;
import com.Assignment.domain.DoctorAppoinmentDate;
import com.Assignment.repository.AppointmentsRepository;
import com.Assignment.repository.DoctorAppoinmentDateRepository;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

@Controller
public class PatientsAppointment {
	
	@Autowired
	AppointmentsRepository appointmentsRepository;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	DoctorAppoinmentDateRepository doctorAppoinmentDateRepository;
	
	
	@RequestMapping("/")
	public String getuserReg(){
					
		return "LoginPage";	
	}
	
	@RequestMapping("/getAppointmentDetails")
	public ModelAndView getAppointmentDetails(@RequestParam("name") String name,@RequestParam("age") String age,@RequestParam("dob") String dob,
	@RequestParam("bloodgroup") String bloodgroup,@RequestParam("address") String address,@RequestParam("mobilenumber")
	String mobilenumber,@RequestParam("email") String email,HttpSession session,HttpServletRequest request, 
    HttpServletResponse response) throws IOException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	      Date date = new Date(); 
	    PdfWriter writer = new PdfWriter("D:/"+name+".pdf");
	    Document document = new Document(new PdfDocument(writer), PageSize.A4);
		
	    Table table = new Table(new float[]{3,3,3,3,3,3,3,3});
	    table.setWidth(UnitValue.createPercentValue(100));
	    PdfFont bold = PdfFontFactory.createFont("Times-Bold");
	    table.addHeaderCell(new Cell().add(new Paragraph("Name").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Age").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("DOB").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Blood Group").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Address").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Mobile Number").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Email").setFont(bold)));
	    table.addHeaderCell(new Cell().add(new Paragraph("Date of Appointment ").setFont(bold)));
	   
	      table.addCell(name);
	      table.addCell(age);
	      table.addCell(dob);
	      table.addCell(bloodgroup);
	      table.addCell(address);
	      table.addCell(mobilenumber);
	      table.addCell(email);
	      table.addCell(formatter.format(date));
	    
	    
	    document.add(table);
	    document.close();
	
	    Appointments appointments=new Appointments();
	    appointments.setAppointment_Date(formatter.format(date));
	    appointments.setMobile_Number(mobilenumber);
	    appointments.setName(name);
	    appointments.setAppointment_File_Path("D:/"+name+".pdf");
	    appointmentsRepository.save(appointments);
	    
	   List<DoctorAppoinmentDate> listavailableDate = doctorAppoinmentDateRepository.listDate();
	   System.out.println("listavailableDate:"+listavailableDate);
	   	
	   for(DoctorAppoinmentDate e:listavailableDate) {
	    if(e.getAvailable_Date().equals(formatter.format(date))) {
	    	SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Appointment confirmation");
			String messageBody = "Hello, \n";
    		
	    	messageBody += "Your Appointment confirmation : Doctor Is Availabe ";
	    	message.setText(messageBody);
	    	javaMailSender.send(message);
	    }
	    else
	    {
	    	SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Appointment confirmation");
			String messageBody = "Hello, \n";
	    	
	    	messageBody += "Your Appointment confirmation : Doctor Is Not Availabe ";
	    	message.setText(messageBody);
	    	javaMailSender.send(message);
	    }
	   }
		return new ModelAndView("SuccessfulPage");
	}

}
