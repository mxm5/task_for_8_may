package io.mxm.testers.service;

import io.mxm.testers.config.security.JwtUtils;
import io.mxm.testers.config.security.UsersService;
import io.mxm.testers.domains.Identity;
import io.mxm.testers.dto.ResponseDto;
import io.mxm.testers.dto.Token;
import io.mxm.testers.dto.UsernameAndPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class IdentityService implements IIdentityService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersService usersService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public ResponseDto login(UsernameAndPasswordDTO usernameAndPasswordDTO) {
        tryToAuthenticate(usernameAndPasswordDTO);
        UserDetails userDetails = usersService.loadUserByUsername(usernameAndPasswordDTO.getUsername());
        Identity identity = (Identity) userDetails;

        ResponseDto responseDto = new ResponseDto();
        ;
        Token token = new Token(jwtUtils.generateToken(identity));
        responseDto.setData(token);
        return responseDto;

    }

    private void tryToAuthenticate(UsernameAndPasswordDTO usernameAndPasswordDTO) {
        try {
             authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usernameAndPasswordDTO.getUsername(), usernameAndPasswordDTO.getPassword()
                    )
            );


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(" bad credentials ");
        }
    }
}
