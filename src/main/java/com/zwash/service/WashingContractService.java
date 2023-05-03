package com.zwash.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.zwash.pojos.WashingContract;

@Service
public interface WashingContractService extends Serializable{
    public void saveWashingContract(WashingContract washingContract);
    public List<WashingContract> getAllWashingContracts();
    public WashingContract getWashingContractById(long id) throws Exception;
    public void deleteWashingContract(long id) throws Exception;
	WashingContract createWashingContract(WashingContract washingContract);
	WashingContract updateWashingContract(long contractId, WashingContract washingContractDetails) throws Exception;
}
