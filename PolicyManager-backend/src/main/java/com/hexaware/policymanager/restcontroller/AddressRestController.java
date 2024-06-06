package com.hexaware.policymanager.restcontroller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.exception.AddressNotFoundException;
import com.hexaware.policymanager.exception.DuplicateUserException;
import com.hexaware.policymanager.service.IAddressService;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/address")
public class AddressRestController {
    @Autowired
    IAddressService addressService;
 
    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<Address> createAddress(@RequestBody AddressDTO addressDTO) throws DuplicateUserException {
        Address address = addressService.createAddress(addressDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
 
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<Address> updateAddress(@RequestBody AddressDTO addressDTO) throws AddressNotFoundException {
        Address address = addressService.updateAddress(addressDTO);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
 
    @DeleteMapping("/delete/{addressId}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<Void> deleteByAddressId(@PathVariable long addressId) throws AddressNotFoundException {
        addressService.deleteByAddressId(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    @GetMapping("/get-by-id/{addressId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public ResponseEntity<AddressDTO> getByAddressId(@PathVariable long addressId) throws AddressNotFoundException {
        AddressDTO addressDTO = addressService.getByAddressId(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.OK);
    }
 
    @GetMapping("/get-by-state/{state}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Address>> getAddressByState(@PathVariable String state) throws AddressNotFoundException {
        List<Address> addresses = addressService.getByState(state);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
 
    @GetMapping("/get-by-city/{city}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Address>> getAddressByCity(@PathVariable String city) throws AddressNotFoundException {
        List<Address> addresses = addressService.getByCity(city);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
 
    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Address>> getAllAddress() {
        List<Address> addresses = addressService.getAllAddress();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}