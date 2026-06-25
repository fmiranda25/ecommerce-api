package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Profile;
import org.yearup.service.ProfileService;
import org.yearup.models.User;
import org.yearup.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("")
    public Profile getProfile(Principal principal) {
        // get the currently logged in username
        String userName = principal.getName();
        // find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        Profile profile = profileService.getById(userId);
        return profile;
    }


    @PutMapping("")
    public Profile updateProfile(Principal principal, @RequestBody Profile profile) {
        // get the currently logged in username
        String userName = principal.getName();
        // find database user by username
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return profileService.update(userId, profile);
    }


}
