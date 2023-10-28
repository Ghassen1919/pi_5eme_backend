package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @PreAuthorize("hasRole('Admin')") // Ensure that only admin users can access this endpoint
    @PostMapping("/block/{userId}")
    public void blockUser(@PathVariable int userId) {
        userService.blockUser(userId);

    }
    @PreAuthorize("hasRole('Admin')") // Ensure that only admin users can access this endpoint
    @PostMapping("/unblock/{userId}")
    public void unblockUser(@PathVariable int userId) {
        userService.unblockUser(userId);

    }
     // Ensure that only admin users can access this endpoint
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/forgot/{mail}")
    public void forgotpassword(@PathVariable String mail) {
        userService.forgotPassword(mail);

    }
    @GetMapping("/afficher")
    @PreAuthorize("hasRole('Admin')")
    public List<User> afficher() {

        return userService.selectAll();
    }
    @PutMapping("/modifier/{id}")

    public void modifier(@PathVariable("id") int id ,
                         @RequestParam("oldpassword") String oldpassword,
                         @RequestParam("newpassword") String newpassword,
                         @RequestParam("newpassword1") String newpassword1) {

        userService.modifier(id,oldpassword,newpassword,newpassword1);
    }



    @PutMapping("/changeph/{id}")
    @PreAuthorize("hasRole('User')")
    public void add(@RequestParam("file") MultipartFile file,
                    @PathVariable("id") int id )
    {
        userService.changeph(file,id);

    }

    @GetMapping("/getcurrentuser")

    public User getcurrentuser() {

        return userService.getCurrentUser();
    }
    @GetMapping("/search")
    @PreAuthorize("hasRole('Admin')")
    public List<User> searchuser(@RequestParam("ref") String q) {
        return userService.search(q);
    }


    @GetMapping("/nbuser")
    public Float getnbusers(){
        List<User> myObjects = userService.selectAll();
        float count=0;
        for (User claim : myObjects) {
            count=count +1;

        }
        return count;

    }
}
