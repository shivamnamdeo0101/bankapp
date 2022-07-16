package com.shivam.bankapp.dto;

import jdk.jfr.BooleanFlag;
import lombok.*;

import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class AccountDetailsDto {

    @Positive
    private Long srcId;

    @Positive
    private Long dstId;

    @Positive
    private String password;

    @Positive
    private Double amt;

    @BooleanFlag
    private boolean active;

    @BooleanFlag
    private boolean blocked;
}
