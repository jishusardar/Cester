package me.projects.Transactions.Service;

import me.projects.Transactions.Entity.Transactions;
import me.projects.Transactions.Repository.TransactionRepository;
import me.projects.Transactions.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    public TransactionResponse createTransaction(Transactions transactions){
        Transactions transaction=transactionRepository.save(transactions);
        TransactionResponse result=MapRequestToResponse(transaction);
        return result;
    }
    public TransactionResponse MapRequestToResponse(Transactions transactions){
        return new TransactionResponse(transactions.getId(),transactions.getAmount(),transactions.getStatus(),"Transaction Successful");
    }
}
