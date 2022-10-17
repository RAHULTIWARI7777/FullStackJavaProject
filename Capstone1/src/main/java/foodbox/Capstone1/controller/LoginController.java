package foodbox.Capstone1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import foodbox.Capstone1.global.GlobalData;
import foodbox.Capstone1.model.Role;
import foodbox.Capstone1.model.User;
import foodbox.Capstone1.repository.RoleRepository;
import foodbox.Capstone1.repository.UserRepository;


@Controller
public class LoginController {
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
	   
	@Autowired
	 private UserRepository userRepository;
	   
	 @Autowired  
	  private RoleRepository  roleRepository ;
	   @GetMapping("/login")
	   public String getlogin() {
		   GlobalData.cart.clear();
		   return "login";
	   }
	   
	   @GetMapping("/register")
	   public String getregister() {
		   return "register";
	   }
	   
	   @PostMapping("/register")
	   public String postregister(@ModelAttribute("user") User user ,HttpServletRequest request)throws ServletException {
		    String password = user.getPassword();
		    user.setPassword(bCryptPasswordEncoder.encode(password));
		    List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            user.setRoles(roles);
            userRepository.save(user);
            request.login(user.getEmail() ,password);
		   return "redirect:/";
	   } 
}
