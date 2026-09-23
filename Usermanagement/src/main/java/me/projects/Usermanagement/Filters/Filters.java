package me.projects.Usermanagement.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class Filters implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        HttpServletResponse httpServletResponse=(HttpServletResponse) response;
        //Only Microservice Can Communicate To Direct Api Calls
        String microservice=httpServletRequest.getHeader("X-Microservice-Name");
        if(!microservice.equals("AICODETESTING")|| microservice==null|| microservice.isBlank()){
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("{\n" +
                    "    \"Error\":\"bad Request\",\n" +
                    "    \"Reason\":\"No Direct Calls\"\n" +
                    "}");
        }
        chain.doFilter(request,response);
    }
}
