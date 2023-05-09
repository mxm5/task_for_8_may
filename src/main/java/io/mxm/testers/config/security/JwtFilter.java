package io.mxm.testers.config.security;



import io.mxm.testers.domains.Identity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UsersService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest
            , HttpServletResponse httpServletResponse
            , FilterChain filterChain) throws ServletException, IOException {


        String jwt = httpServletRequest.getHeader("Authorization");
        String remoteAddress = httpServletRequest.getRemoteAddr();
        log.info(remoteAddress);
        String jwtParam = httpServletRequest.getParameter("jwt");
        if (jwt == null && jwtParam != null) {
            jwt = jwtParam;
        }
        log.info("jwt is : " + jwt);
        if (jwt == null) {
            log.error(" jwt token was null ");
        } else if (jwt.equals("")) {
            log.error(" jwt token was empty string");
        } else if (!jwt.startsWith("Bearer ")) {
            log.info(" jwt is not in correct format");
        } else {
            log.info("trying to read the jwt");
            try {
                log.info("getting subject of token");
                String sub = jwtUtils.getSubject(jwt.substring(7));

                if (!StringUtils.isEmpty(sub)
                        && SecurityContextHolder.getContext().getAuthentication() == null) {

                    Identity user = userService.findUserBySubject(sub);
                    log.info("user found was " + user);
                    log.info("trying to get user from database by sub");
                    if (user != null) {
                        log.info("user was not null");
                        UsernamePasswordAuthenticationToken
                                usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        user.getAuthorities()
                                );
                        log.info("UnameAndPassAuth is : " + usernamePasswordAuthenticationToken);
                        log.info("trying to set auth to security context holder ");
                        SecurityContextHolder.getContext().setAuthentication(
                                usernamePasswordAuthenticationToken
                        );
                        log.info("security context holder changed to :" + SecurityContextHolder.getContext().getAuthentication());
                    }
                }
            } catch (Exception e) {
                log.error("exception happened");
                log.error(Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
        }
        log.info("filter is passing without exception");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

