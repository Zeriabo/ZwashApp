package com.zwash.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.pojos.Wash;
import com.zwash.repository.WashRepository;
import com.zwash.service.WashService;

@Service
public class WashServiceImpl implements WashService {
	
	@Autowired
	 WashRepository washRepository;

	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	public boolean startWash(Wash wash) {
		
		if(wash.getStatus()=="Pending")
		{
			   wash.setStatus("Executing");
		       wash.setStartTime(LocalDateTime.now());   
		       return true;
		        
		}else {
			return false;
		}
     
	}
	
	@Override
	public boolean finishwash(Wash wash) {
		
		if(wash.getStatus()=="Executing")
		{
			   wash.setStatus("Finished");
		       wash.setEndTime(LocalDateTime.now());   
		       return true;
		        
		}else {
			return false;
		}
     
	}


	@Override
	public boolean cancelwash(Wash wash) {
		
		if(wash.getStatus()!="Finished" || wash.getStatus()!="Executing")
		{
			   wash.setStatus("Cancelled");
		       return true;
		        
		}else {
			return false;
		}
     
	}
	
	@Override
	public boolean reschdulelwash(Wash wash, LocalDateTime startTime) {
		
		if(wash.getStatus()!="Finished" || wash.getStatus()!="Executing")
		{
			
		wash.setStartTime(startTime);
		washRepository.save(wash);
		return true;
		        
		}else {
			return false;
		}
     
	}
	

}
