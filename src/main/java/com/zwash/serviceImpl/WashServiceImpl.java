package com.zwash.serviceImpl;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwash.pojos.Wash;
import com.zwash.pojos.WashStatus;
import com.zwash.repository.WashRepository;
import com.zwash.service.WashService;

@Service
public class WashServiceImpl implements WashService {

    private static final long serialVersionUID = 1L;
	@Autowired
    private WashRepository washRepository;

    @Override
    public boolean startWash(Wash wash) {
        if (wash.getStatus().equals(WashStatus.QUEUING)){
            wash.setStatus(WashStatus.WASHING);
            wash.setStartTime(LocalDateTime.now());
        	
            washRepository.save(wash);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean finishWash(Wash wash) {
        if (wash.getStatus().equals(WashStatus.QUEUING)||wash.getStatus().equals(WashStatus.WASHING)) {
            wash.setStatus(WashStatus.FINISHED);
            wash.setEndTime(LocalDateTime.now().plusMinutes(20));
            washRepository.save(wash);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean cancelWash(Wash wash) {
        if (!wash.getStatus().equals(WashStatus.FINISHED) && !wash.getStatus().equals(WashStatus.WASHING)) {
            wash.setStatus(WashStatus.CANCELED);
            washRepository.save(wash);
            return true;
 
        } else {
            return false;
        }
    }

    @Override
    public boolean rescheduleWash(Wash wash, LocalDateTime startTime) {
        if (!wash.getStatus().equals(WashStatus.FINISHED) && !wash.getStatus().equals(WashStatus.QUEUING)) {
            wash.setStartTime(startTime);
            washRepository.save(wash);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Wash getWash(Long id) throws NoSuchElementException {
        return washRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Wash not found"));
    }
}
