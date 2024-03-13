package com.hexaware.policymanager.restcontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.policymanager.service.IProfilePictureService;
import com.hexaware.policymanager.service.IUsersService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1/profilepic")
public class ProfilePictureRestController {

	@Autowired
	IProfilePictureService profilePictureService;

	@Autowired
	IUsersService usersService;

	@PostMapping("/upload")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<String> uploadProfilePic(@RequestParam("profilepic") MultipartFile picture,
			Authentication authentication) throws IOException, SerialException, SQLException {
		if (picture != null) {
			try {
				boolean uploadSuccess = profilePictureService.uploadProfilePic(picture); // Pass the user DTO to the
																							// service method
				if (uploadSuccess) {
					return ResponseEntity.ok("Uploaded profile picture successfully: " + picture.getOriginalFilename());
				} else {
					return ResponseEntity.badRequest()
							.body("Could not upload profile picture: " + picture.getOriginalFilename());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.badRequest()
						.body("Could not upload profile picture: " + picture.getOriginalFilename());
			}
		} else {
			return ResponseEntity.badRequest().body("No picture uploaded");
		}
	}

	@GetMapping("/getpic")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<byte[]> getProfilePic() throws Exception {
	    ResponseEntity<ByteArrayResource> response = profilePictureService.getPic();

	    HttpHeaders headers = response.getHeaders();
	    MediaType contentType = headers.getContentType();

	    if (contentType != null && (MediaType.IMAGE_JPEG.equals(contentType) || MediaType.IMAGE_PNG.equals(contentType))) {
	        // Content type is image/jpeg or image/png, indicating that the response contains an image
	        byte[] imageData = response.getBody().getByteArray();
	        return ResponseEntity.ok()
	                             .contentType(contentType)
	                             .body(imageData);
	    } else {
	        // Content type is not an image format
	        throw new Exception("Response does not contain an image");
	    }
	}


}