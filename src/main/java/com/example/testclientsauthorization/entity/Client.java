package com.example.testclientsauthorization.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Clients")
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "ClientLogin", nullable = false, unique = true)
    @Size(min = 5, max = 25)
    private String clientLogin;

    @Column(name = "ClientPassword", nullable = false)
    @Size(min = 8, max = 18)
    private String clientPassword;

    @Column(name = "LastName", nullable = false)
    @Size(min = 2, max = 36)
    private String lastName;

    @Column(name = "FirstName", nullable = false)
    @Size(min = 1, max = 49)
    private String firstName;

    @Column(name = "Patronymic")
    @Size(max = 100)
    private String patronymic;

    @Column(name = "Age", nullable = false)
    //нет ограничения на размер, потому что int
    private int age;

    @Column(name = "EmailAddress", nullable = false)
    //не ставим ограничение на размер, потому что... просто это email
    private String emailAddress;

    @Column(name = "PhoneNumber", nullable = false)
    @Size(min = 11, max = 12)
    private String phoneNumber;

    @Column(name = "PassportSeries")
    @Size(min = 4, max = 4)
    private String passportSeries;

    @Column(name = "PassportNumber")
    @Size(min = 6, max = 6)
    private String passportNumber;

}
