package pl.coderslab.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data //Lombok sam generuje settery, gettery, toString i wiele innych
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String firstName;
    String lastName;
    String gender;
    String country;
    String notes;
    boolean mailingList;
    List programmingSkills;
    List hobbies;
}
