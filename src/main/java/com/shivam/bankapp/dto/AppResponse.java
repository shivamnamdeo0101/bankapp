package com.shivam.bankapp.dto;

import lombok.*;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppResponse {
    private String sts;
    private String msg;
}
