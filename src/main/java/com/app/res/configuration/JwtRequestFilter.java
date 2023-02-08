package com.app.res.configuration;

import com.app.res.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header=request.getHeader("Authorization");
        String jwtToken = null;
        String userName=null;
        if(header!=null && header.startsWith("Bearer ")){
            jwtToken = header.substring(7);

            try {
                userName=jwtUtil.getUserNameFromToken(jwtToken);

            }catch (IllegalArgumentException e){
                System.out.println("Unable to get JwtToken");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt exception is expired");
            }
        }
        else {
            System.out.println("jwt token does not start wit Bearer");
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails = jwtService.loadUserByUsername(userName);

           if(jwtUtil.validateToken(jwtToken,userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
           usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
         filterChain.doFilter(request,response);
    }
}
