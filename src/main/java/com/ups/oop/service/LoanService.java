package com.ups.oop.service;

import com.ups.oop.dto.LoanDTO;
import com.ups.oop.entity.Loan;
import com.ups.oop.repository.LoanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanDTO> getLoans() {
        Iterable<Loan> loanIterable = loanRepository.findAll();
        List<LoanDTO> loans = new ArrayList<>();
        for(Loan loan : loanIterable) {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setSerial(loan.getSerial());
            loanDTO.setLoanDate(loan.getLoanDate().toString());
            loanDTO.setDays(loan.getDays());
            loanDTO.setClient(loan.getClient().getName() + "-" + loan.getClient().getLastname());
            loanDTO.setWorker(loan.getWorker().getName() + "-" + loan.getWorker().getLastname());
            loans.add(loanDTO);
        }
        return loans;
    }

    public ResponseEntity getAllLoans() {
        List<LoanDTO> loans = getLoans();
        if(loans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loans Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(loans);
    }
}
