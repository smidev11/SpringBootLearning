package com.tech.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.entity.Degreasing;
import com.tech.entity.DegreasingRepository;
import com.tech.entity.GenRef;
import com.tech.entity.GenRefRepository;
import com.tech.entity.Job;
import com.tech.entity.JobRepository;
import com.tech.entity.Part;
import com.tech.entity.Service;
import com.tech.entity.StudentRepository;
import com.tech.pojo.CustomResp;
import com.tech.report.CreateReport;

@RestController
public class MainController {
	
	@Autowired
	GenRefRepository genRefRepository;
	@Autowired
	StudentRepository repository;
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	DegreasingRepository degreasingRepo;
	
	
	
	
	@Autowired
	ServletContext context;
	private static final String APPLICATION_MS_WORD_VALUE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String goToHomePage() {
		
		
		try{
			
			GenRef ref = new GenRef();
			ref.setClientName("TestC");
			ref.setAddress("PPPune");
			
			ref.setTcno("TC-999");
			
			Part p1 = new Part();
			p1.setInspector("INS1");
			p1.setPartname("Part1");
			p1.setGenref(ref);
			Part p2 = new Part();
			p2.setPartname("Part2");
			p2.setInspector("ins2");
			p2.setRevNo("3");
			p2.setGenref(ref);
			
			List<Part> plist = new ArrayList<>();
			plist.add(p2);
			
			plist.add(p1);
			ref.setPartList(plist);
			
			Job j = new Job();
			j.setClientName(ref.getClientName());
			j.setDate(new Date());
			j.setTcno(ref.getTcno());
			
			j.setGenref(ref);
			ref.setJob(j);
			
			jobRepository.saveAndFlush(j);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//genRefRepository.save(g);
		
		return "Welcome here!!";
	}
	
	
	
	
	@RequestMapping(value = "/job",  method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> addJob(@RequestBody GenRef genRef) {
		try{
			
			Job j = new Job();
			j.setClientName(genRef.getClientName());
			j.setDate(new Date());
			j.setTcno(genRef.getTcno());
			j.setGenref(genRef);
			genRef.setJob(j);
			
			try{
				jobRepository.saveAndFlush(j);	
			}catch (Exception e){
				e.printStackTrace();
			}
			CustomResp resp = new CustomResp();
			resp.setMessage("Job Created");
			
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
			//return ResponseEntity.ok().body("{New Jobs added successfully");
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to save General");
		}
	}
	
	
	@RequestMapping(value = "/job",  method = RequestMethod.GET, headers = "Accept=application/json")
	@Transactional
	public List<Job> getAlljob() {
		List<Job> jobs = new ArrayList<>();
		jobs = jobRepository.findAll();
		return jobs;
	}
	
	@RequestMapping(value = "/download", produces = APPLICATION_MS_WORD_VALUE)
    public ResponseEntity<byte[]> downloadFile(String jobId, HttpServletRequest req) throws IOException {
    	
    	Long longId = Long.parseLong(jobId);
    	Optional<Job> job = jobRepository.findById(longId);
    	CreateReport report = new CreateReport();
    	String fileName = "D:\\del\\am\\"+jobId+".docx";
    	//String fileName = "D:\\del\\am\\Test.txt";
    	report.createReport(job.get(), fileName);
    	String displayFileName = job.get().getClientName();
    	displayFileName = displayFileName.replace(" ", "_");
		byte[] content = FileCopyUtils.copyToByteArray(new File(fileName));
	    return ResponseEntity.ok()
	                         .contentLength(content.length)
	                         .header(HttpHeaders.CONTENT_TYPE, APPLICATION_MS_WORD_VALUE)
	                         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+displayFileName+".docx")
	                         .body(content);
    }
    
    @RequestMapping(value = "/degreasing",  method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> saveDegreasing(@RequestBody List<Degreasing> deagreasingList,HttpServletRequest req) {
		try{
			
			System.out.println("");
			Long jobId= 1L;
			Optional<Job> job = jobRepository.findById(jobId);
			Job j = job.get();
			
			for(Degreasing deg : deagreasingList){
				Degreasing temp = new Degreasing();
				temp.setStime(deg.getStime());
				temp.setEndtime(deg.getEndtime());
				temp.setMethoddegreasing(deg.getMethoddegreasing());
				temp.setQnt(deg.getQnt());
				temp.setOperator(deg.getOperator());
				temp.setOperationtime(deg.getOperationtime());
				temp.setJob(j);
				//j.getDegreasing().add(temp);
				
				/*Job jn = new Job();
				jn.setClientName("New1");
				jn.setDate(new Date());
				jn.setTcno("hh");
				jobRepository.saveAndFlush(jn);
				System.out.println("");*/
				//jobRepository.save(j);
				Degreasing d = degreasingRepo.saveAndFlush(temp);
				System.out.println(d.getId());
			}
			//j.setDegreasing(deagreasingList);
			
			//degreasingRepo.saveAll(deagreasingList);
			CustomResp resp = new CustomResp();
			resp.setMessage("Degreasing saved");
			
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to save General");
		}
	}
}
