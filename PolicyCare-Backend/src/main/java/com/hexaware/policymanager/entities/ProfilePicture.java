package com.hexaware.policymanager.entities;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Profile")
public class ProfilePicture {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictureSeqGenerator")
	@SequenceGenerator(name = "pictureSeqGenerator", sequenceName = "picture_sequence", allocationSize = 1, initialValue = 1)
	private long pictureId;

	private String name;

	private String type;

	@JsonIgnore
	@Lob
	private Blob data;

	@OneToOne(mappedBy = "profile")
	@JsonBackReference(value = "Users-Profile")
	private Users user;

	public ProfilePicture() {
		super();
	}

	public ProfilePicture(String name, String type, Blob data) {
		super();
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public long getPictureId() {
		return pictureId;
	}

	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ProfilePicture [pictureId=" + pictureId + ", name=" + name + ", type=" + type + ", data=" + data + "]";
	}
	
	public String getDataAsBase64() throws SQLException, IOException {
        if (data != null) {
            byte[] bytes = data.getBytes(1, (int) data.length());
            return Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }

}