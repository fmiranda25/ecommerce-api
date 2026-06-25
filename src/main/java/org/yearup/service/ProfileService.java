package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getById(int userId) {
        return profileRepository.findById(userId).orElse(null);
    }


    public Profile update(int userId, Profile updatedProfile) {
        Profile existing = profileRepository.findById(userId).orElseThrow();
        existing.setFirstName(updatedProfile.getFirstName());
        existing.setLastName(updatedProfile.getLastName());
        existing.setPhone(updatedProfile.getPhone());
        existing.setEmail(updatedProfile.getEmail());
        existing.setAddress(updatedProfile.getAddress());
        existing.setCity(updatedProfile.getCity());
        existing.setState(updatedProfile.getState());
        existing.setZip(updatedProfile.getZip());
        return profileRepository.save(existing);
    }
}
