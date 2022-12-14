package foodbox.Capstone1.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import foodbox.Capstone1.model.Role;
import foodbox.Capstone1.model.User;
import foodbox.Capstone1.repository.RoleRepository;
import foodbox.Capstone1.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
     
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
		String  email  =  token.getPrincipal().getAttributes().get("email").toString();
		if(userRepository.findUserByEmail(email).isPresent()) {
			
		}
		else {
			User user = new User();
			user.setFirstName(token.getPrincipal().getAttributes().get("given_Name").toString());
			user.setLastName(token.getPrincipal().getAttributes().get("family_Name").toString());
            user.setEmail(email);
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            userRepository.save(user);
			
		}
		redirectStrategy.sendRedirect(request, response, "/");
		
	}

	
    
    
    
    
    
    
    
    
    
    
}