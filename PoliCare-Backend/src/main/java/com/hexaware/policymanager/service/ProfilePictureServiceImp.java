package com.hexaware.policymanager.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.policymanager.config.UserInfoUserDetails;
import com.hexaware.policymanager.entities.ProfilePicture;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.ProfilePictureRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfilePictureServiceImp implements IProfilePictureService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ProfilePictureRepository profilePictureRepository;

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	private UserDetails getCurrentUser() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    if (principal instanceof UserDetails) {
	        return (UserDetails) principal;
	    } else {
	        return null;
	    }
	}
	@Override
	public boolean uploadProfilePic(MultipartFile picture) throws IOException, SerialException, SQLException {
	    String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
	    UserDetails userDetails = getCurrentUser();
	    if (userDetails != null && userDetails instanceof UserInfoUserDetails) {
	        UserInfoUserDetails userInfo = (UserInfoUserDetails) userDetails;
	        Users user = usersRepository.getUserByEmailAddress(userInfo.getUsername()); // Assuming this method exists in UserRepository
	        if (user != null) {
	            ProfilePicture pic = new ProfilePicture(fileName, picture.getContentType(), new SerialBlob(picture.getBytes()));
	            pic.setUser(user);
	            try {
	                user.setProfile(pic);
	                usersRepository.save(user);
	                return true; // Return true if the profile picture is successfully uploaded
	            } catch (Exception e) {
	                e.printStackTrace();
	                return false; // Return false if an exception occurs during saving
	            }
	        }
	    }
	    return false; // Return false if the user is null or not found
	}



	@Override
	public ResponseEntity<ByteArrayResource> getPic() throws Exception {
	    try {
	        // Get the current authenticated user
	        UserDetails userDetails = getCurrentUser();

	        // Check if userDetails is an instance of UserInfoUserDetails
	        if (userDetails instanceof UserInfoUserDetails) {
	        	 UserInfoUserDetails userInfo = (UserInfoUserDetails) userDetails;
	 	        Users user = usersRepository.getUserByEmailAddress(userInfo.getUsername()); 
	 	        ProfilePicture profilepic=user.getProfile();
	            // Retrieve the picture ID from user details
	            Long pictureId = profilepic.getPictureId();
	            System.out.println("Fetching profile picture with ID: " + pictureId);
	           
	            // Retrieve the profile picture from the repository
	            Optional<ProfilePicture> pic = profilePictureRepository.findById(pictureId);
	            if (pic.isPresent()) {
	                ProfilePicture profile = pic.get();
	                byte[] imageData = profile.getData().getBytes(1, (int) profile.getData().length());
	                // Return the profile picture as a ByteArrayResource
	                return ResponseEntity.ok()
	                    .contentType(MediaType.parseMediaType(profile.getType()))
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + profile.getName() + "\"")
	                    .body(new ByteArrayResource(imageData));
	            } else {
	                // Profile picture not found
	                throw new Exception("Profile picture not found");
	            }
	        } else {
	            // User details not found or not instance of UserInfoUserDetails
	            throw new Exception("User details not found or invalid");
	        }
	    } catch (Exception e) {
	        throw new Exception("Error downloading file", e);
	    }
	}


}