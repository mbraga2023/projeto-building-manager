package com.projetobuildingmanager.projetobuildingmanager.models;

// import java.util.Date;

// import org.springframework.format.annotation.DateTimeFormat;

// import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.persistence.Temporal;
// import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid Email")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Invalid Password")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Invalid name")
    private String name;

    @Column(nullable = false)    
    @NotEmpty(message = "Invalid Unit number")
    private String unitNumber;

    // @Basic
    // @Temporal (TemporalType.DATE)
    // @DateTimeFormat(pattern = "dd/MM/yyy")
    // private Date data;

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public Date getData() {
    //     return data;
    // }

    // public void setData(Date data) {
    //     this.data = data;
    // }

   
    
}
