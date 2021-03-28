package pl.coderslab.entity;

import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @NotNull
    @REGON
    private String regon;

    @NotNull
    @NIP
    private String nip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
//Utwórz encje o nazwie Publisher.
//Ustal nazwę tabeli bazy danych dla tej encji na publishers.
//Encja ma zawierać następujące pola:
//id
//name
//Utwórz klasę PublisherDao - służącą do operacji na tej encji.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).