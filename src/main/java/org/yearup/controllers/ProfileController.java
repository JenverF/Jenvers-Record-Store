package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    // add a get method to get a user's profile
    @GetMapping
    public Profile getProfile(Principal principal) {
        String username = principal.getName();
        User user = userDao.getByUserName(username);
        int userId = user.getId();

        return profileDao.getByUserId(userId);
    }

    // add a put method to update an existing user's profile
    @PutMapping
    public void updateProfile(Principal principal, @RequestBody Profile profile) {
        String username = principal.getName();
        User user = userDao.getByUserName(username);
        int userId = user.getId();

        profileDao.update(userId, profile);
    }
}
