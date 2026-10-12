package me.projects.Transactions.Service;

import lombok.RequiredArgsConstructor;
import me.projects.Transactions.Entity.Transactions;
import me.projects.Transactions.Repository.TransactionRepository;
import me.projects.Transactions.dto.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionResponse createTransaction(Transactions transactions){
        Transactions transaction=transactionRepository.save(transactions);
        TransactionResponse result=MapRequestToResponse(transaction);
        return result;
    }
    public TransactionResponse MapRequestToResponse(Transactions transactions){
        return new TransactionResponse(transactions.getId(),transactions.getAmount(),transactions.getStatus(),"Transaction Successful");
    }
    public void DeleteTransactions(Long id){
        Transactions transactions=transactionRepository.findByIdAndIsDeletedIsFalse(id).orElseThrow(()-> new RuntimeException("Transaction Id is Invalid"));
        transactionRepository.delete(transactions);
    }
}
