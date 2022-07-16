package com.shivam.bankapp.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountDto {

    @Positive
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Positive
    private Double balance;

    @NotNull
    private Boolean isAdmin = false;

    @NotNull
    private Boolean isBlocked = false;

    @NotNull
    private Boolean isActive = false;
}
