package com.config;

/**
 * Created by hsenid on 5/4/17.
 */
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected LoginSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        handle(request, response, authentication);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
        String targetUrl = "";
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            switch(grantedAuthority.getAuthority()){
                case "ROLE_USER":
                    targetUrl="/hello";
                    //Assign your url here
                    break;
                case "ROLE_ADMIN":
                   targetUrl= "/admin";
                    //Assign your URL here
                    break;
            }
        }
        return targetUrl;
    }
}