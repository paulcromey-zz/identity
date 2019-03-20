package com.cromey.identity.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@SuppressWarnings("serial")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@JsonPropertyOrder({ "id", "userName", "password", "email", "dateOfBirth" })
@Entity
@Table(name = "users")
public class User implements Serializable {

  @Column(name = "date_of_birth")
  @Past(message = "Date of Birth must be in the past")
  private LocalDate dateOfBirth;

  @Column(name = "email")
  @NotBlank(message = "Email is mandatory")
  @Email(message = "Email should be valid")
  private String email;

  @Id
  @Type(type = "pg-uuid")
  private UUID id;

  @Column(name = "password")
  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
  private String password;

  @Column(name = "username")
  @NotBlank(message = "UserName is mandatory")
  private String userName;

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public UUID getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
