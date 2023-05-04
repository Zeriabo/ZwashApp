package com.zwash.service;

import java.io.Serializable;
import java.util.List;
<<<<<<< HEAD
import com.zwash.pojos.WashingContract;

=======
import org.springframework.stereotype.Service;
import com.zwash.pojos.WashingContract;

@Service
>>>>>>> e11520111fcd663b55fb6071772482e77369f442
public interface WashingContractService extends Serializable{
    public void saveWashingContract(WashingContract washingContract);
    public List<WashingContract> getAllWashingContracts();
    public WashingContract getWashingContractById(long id) throws Exception;
    public void deleteWashingContract(long id) throws Exception;
	WashingContract createWashingContract(WashingContract washingContract);
	WashingContract updateWashingContract(long contractId, WashingContract washingContractDetails) throws Exception;
}
