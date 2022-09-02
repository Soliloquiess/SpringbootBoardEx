package com.spring.boot.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Vo_member {

     private String  memberId;
     private String  loginId;
     private String  password;
     private String  name;
     private String  role;
     private String  regDay;

    @Builder
    public Vo_member( String loginId, String password, String name, String role, String regDay) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.regDay = regDay;
    }
}
