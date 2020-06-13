package org.csu.jpetstore.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "User's information")
public class Account {
    private Integer id;
    @ApiModelProperty(value = "Username",required = true)
    private String username;
    @ApiModelProperty(value = "Password",required = true)
    private String password;
    @ApiModelProperty(value = "Email",required = true)
    private String email;
    @ApiModelProperty(value = "First Name",required = true)
    private String firstName;
    @ApiModelProperty(value = "Last Name",required = true)
    private String lastName;
    private boolean status;
    @ApiModelProperty(value = "Address1",required = true)
    private String address1;
    @ApiModelProperty(value = "Address2",required = true)
    private String address2;
    @ApiModelProperty(value = "City",required = true)
    private String city;
    @ApiModelProperty(value = "State",required = true)
    private String state;
    @ApiModelProperty(value = "Zipcode",required = true)
    private String zip;
    @ApiModelProperty(value = "Country",required = true)
    private String country;
    @ApiModelProperty(value = "Phone number",required = true)
    private String phone;
    private String role;

    public Account() {
    }

    public Account(Integer id, String username, String password, String email, String firstName, String lastName, boolean status, String address1, String address2, String city, String state, String zip, String country, String phone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
