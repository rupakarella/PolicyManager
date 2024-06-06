package com.hexaware.policymanager.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IProfilePictureService {
	public boolean uploadProfilePic(MultipartFile picture) throws IOException, SerialException, SQLException;

	public ResponseEntity<ByteArrayResource> getPic() throws Exception;
}