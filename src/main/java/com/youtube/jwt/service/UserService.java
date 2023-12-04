package com.youtube.jwt.service;

import com.youtube.jwt.dao.RoleDao;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public class InternalServerErrorException extends RuntimeException {
        public InternalServerErrorException(String message) {
            super(message);
        }
    }
    public void initRoleAndUser() {

       /* Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserId(1);
        adminUser.setUserName("admin123");
        adminUser.setEmail("addmin.ghassen123@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        adminUser.setUserActive(true);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
   */ }





    @Transactional // Add this annotation to ensure atomicity
    public User registerNewUser(User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            throw new ResourceNotFoundException("User email exists.");
        }
        if (userDao.existsByUserName(user.getUserName())) {
            throw new InternalServerErrorException("Username exists.");
        } else {
            Instrument instrument1 = Instrument.builder().name("Gold").symbole("OANDA:XAUUSD").quantite(10.0F).build();
            Instrument instrument2 = Instrument.builder().name("Apple Inc.").symbole("NASDAQ:AAPL").quantite(10.0F).build();
            Instrument instrument3 = Instrument.builder().name("Bitcoin").symbole("BTCUSD").quantite(10.0F).build();
            Instrument instrument4 = Instrument.builder().name("Ethereum").symbole("BINANCE:ETHUSDT").quantite(10.0F).build();
            Instrument instrument5 = Instrument.builder().name("Microsoft Corporation").symbole("NASDAQ:MSFT").quantite(10.0F).build();
            Instrument instrument6 = Instrument.builder().name("Crude Oil").symbole("TVC:USOIL").quantite(10.0F).build();


            // Create a set of instruments
            Set<Instrument> instruments = new HashSet<>();
            instruments.add(instrument1);
            instruments.add(instrument2);
            instruments.add(instrument3);
            instruments.add(instrument4);
            instruments.add(instrument5);
            instruments.add(instrument6);

            // Create a Portefeuille with a solde of 10000 and the set of instruments
            Portefeuille portefeuille = Portefeuille.builder().solde(10000.0F).instrument(instruments).build();

            user.setPortefeuille(portefeuille);

            // Create a new Role (if needed)
            Role role = roleDao.findById("User").orElseThrow(() -> new ResourceNotFoundException("Role not found."));

            // Set the user's roles
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);

            // Set other properties of the User
            user.setUserActive(true);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));
            // Save the User, which will also save the associated Portefeuille due to CascadeType.ALL
            return userDao.save(user);
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void blockUser(int userId) {
        User user = userDao.findById(userId).get();
        user.setUserActive(false);
        userDao.save(user);
    }

    public void unblockUser(int userId) {
        User user = userDao.findById(userId).get();
        user.setUserActive(true);
        userDao.save(user);
    }

    public void forgotPassword(String mail) {
        // Check if the email exists in the database
        User user = userDao.findByEmail(mail);
        if (user != null) {
            System.out.println(user.getUserName());
            // Generate a new password
            String newPassword = RandomStringUtils.random(8, true, true);

            // Update the user's password
            user.setUserPassword(getEncodedPassword(newPassword));
            userDao.save(user);

            // Send the new password to the user's email
            this.emailService.sendSimpleEmail(mail, "new password", newPassword);


        } else {
            throw new ResourceNotFoundException("User not found with mail: " + mail);
        }
    }

    public List<User> selectAll() {
        return (List<User>) userDao.findUsersByRoleNameAndDescription("User", "Default role for newly created record");
    }

    public void modifier(int id, String oldpassword, String newpassword, String newpassword1) {
        User u = userDao.findById(id).get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordMatches = passwordEncoder.matches(oldpassword,u.getUserPassword());
        if (passwordMatches && Objects.equals(newpassword, newpassword1)) {
            u.setUserPassword(getEncodedPassword(newpassword));
            userDao.save(u);
        }
        else{System.out.println("mdp incorrect ou nouveau password non confirmé");
        }
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username=  ((UserDetails) authentication.getPrincipal()).getUsername();
            User user=userDao.findByUserName(username);
            return user;
        } else {
            return null; // or handle the case where no user is authenticated
        }
    }
    public void changeph(MultipartFile file,int id) {
        User user = userDao.findById(id).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            user.setUserPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userDao.save(user);
    }
    public List<User> search(String q) {
        return userDao.findByUserFirstName(q);
    }
}