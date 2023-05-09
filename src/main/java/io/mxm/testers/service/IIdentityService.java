package io.mxm.testers.service;

import io.mxm.testers.domains.Identity;
import io.mxm.testers.dto.ResponseDto;
import io.mxm.testers.dto.UsernameAndPasswordDTO;

public interface IIdentityService {
    ResponseDto login(UsernameAndPasswordDTO usernameAndPasswordDTO);
}
