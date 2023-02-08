package com.app.res.agents;

import com.app.res.entity.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class agentClass {
    @Id
    @SequenceGenerator(
            name = "agent_sequence",
            sequenceName = "agent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "agent_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String dob;
    private String sex;
    private String password;
    private String telNum;
    private String marital_status;
    private String nationality;
    private String userName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "AGENT_ROLE",
            joinColumns = {
                    @JoinColumn(name="AGENT_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    public agentClass() {
    }


    public agentClass(Long id, String firstName, String lastName, String address, String email, String dob, String sex, String telNum,String marital_status, String nationality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.sex = sex;
        this.telNum = telNum;
        this.marital_status = marital_status;
        this.nationality = nationality;

    }

    public agentClass(String firstName, String lastName, String address, String email, String dob, String sex, String telNum,String marital_status, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dob = dob;
        this.sex = sex;
        this.telNum = telNum;
        this.marital_status = marital_status;
        this.nationality = nationality;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }


    @Override
    public String toString() {
        return "agentClass{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", sex='" + sex + '\'' +
                ", telNum='" + telNum + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
