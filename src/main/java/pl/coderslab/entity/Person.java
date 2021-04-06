package pl.coderslab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
@Data //Lombok sam generuje settery, gettery, toString i wiele innych
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3)
    private String login;

    @NotNull
    @Size(min=6, max=12)
    private String password;

    @NotNull
    @Email
    private String email;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
//Zadanie 3
//Utwórz klasę Person zawierającą pola:
//id
//login
//password
//email
//Utwórz klasę PersonDetails zawierającą pola:
//id
//firstName
//lastName
//streetNumber
//street
//city
//Połącz encje za pomocą relacji @OneToOne.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).