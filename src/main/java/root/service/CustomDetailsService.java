package root.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import root.dao.MySqlDao;
import root.securitypojo.CustomUser;
import root.securitypojo.UserEntity;

@Service
public class CustomDetailsService implements UserDetailsService {
   @Autowired
   MySqlDao oauthDao;

   @Override
   public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
      UserEntity userEntity = null;
      try {
    	  
    	//DB_WAY
        //userEntity = oauthDao.getUserDetails(username);
    	  
    	//IN-MEMORY
    	String password = "vinodpassword";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String bcryptPassword = passwordEncoder.encode(password);
    		
       	userEntity = new UserEntity();
       	userEntity.setUsername("myvinemail@gmail.com");
       	userEntity.setPassword(bcryptPassword);
       	 
        CustomUser customUser = new CustomUser(userEntity);
        return customUser;
      
      } catch (Exception e) {
         e.printStackTrace();
         throw new UsernameNotFoundException("User " + username + " was not found in the database");
      }
   }
} 