package pl.coderslab.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//program do hashowania haseł
public class Main {
    public static void main(String[] args) {
        String hash =new BCryptPasswordEncoder().encode("admin");
        System.out.println(hash);
    }
}
