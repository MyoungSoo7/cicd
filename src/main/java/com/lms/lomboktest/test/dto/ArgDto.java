package com.lms.lomboktest.test.dto;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArgDto {

    private String address;
    private String name;
    private String age;
    private String phone;
    private String email;
    private String etc;
    private String etc2;
    private String etc3;

}
