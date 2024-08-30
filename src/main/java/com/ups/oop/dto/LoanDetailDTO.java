package com.ups.oop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LoanDetailDTO {
    private String loanSerial;
    private String loanClient;
    private String loanDate;
    private String bookName;
    private String author;
}
