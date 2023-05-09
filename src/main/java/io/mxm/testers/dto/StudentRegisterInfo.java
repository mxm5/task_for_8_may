package io.mxm.testers.dto;

import io.mxm.testers.domains.Identity;
import io.mxm.testers.domains.Studnet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRegisterInfo {

    private Integer age;
    private String firstname;
    private String lastname;
    private String username;
    private String password;



}
