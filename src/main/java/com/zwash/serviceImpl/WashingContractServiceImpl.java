package com.zwash.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zwash.pojos.WashingContract;
import com.zwash.repository.WashingContractRepository;
import com.zwash.service.WashingContractService;


@Service
public class WashingContractServiceImpl implements WashingContractService {

    private static final long serialVersionUID = 1L;
	@Autowired
    private WashingContractRepository washingContractRepository;

    @Override
    public List<WashingContract> getAllWashingContracts() {
        return washingContractRepository.findAll();
    }

    @Override
    public WashingContract getWashingContractById(long contractId) throws Exception {
        return washingContractRepository.findById(contractId)
        		 .orElseThrow(() -> new Exception());
    }

    @Override
    public WashingContract createWashingContract(WashingContract washingContract) {
        return washingContractRepository.save(washingContract);
    }

    @Override
    public WashingContract updateWashingContract(long contractId, WashingContract washingContractDetails) throws Exception{
        WashingContract washingContract = washingContractRepository.findById(contractId)
                .orElseThrow(() -> new Exception());

        washingContract.setCar(washingContractDetails.getCar());
        washingContract.setStartDate(washingContractDetails.getStartDate());
        washingContract.setEndDate(washingContractDetails.getEndDate());
        washingContract.setPrice(washingContractDetails.getPrice());

        return washingContractRepository.save(washingContract);
    }

    @Override
    public void deleteWashingContract(long contractId) throws Exception {
        WashingContract washingContract = washingContractRepository.findById(contractId)
                .orElseThrow(() -> new Exception());

        washingContractRepository.delete(washingContract);
    }

    @Override
    public void saveWashingContract(WashingContract washingContract) {
        washingContractRepository.save(washingContract);
    }
}