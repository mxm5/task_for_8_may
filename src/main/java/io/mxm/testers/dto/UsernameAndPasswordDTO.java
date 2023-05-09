package io.mxm.testers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsernameAndPasswordDTO {
    private String username;
    private String password;
}
