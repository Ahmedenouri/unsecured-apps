package ma.enset.hospitalapp;

import lombok.AllArgsConstructor;
import ma.enset.hospitalapp.entities.Patient;
import ma.enset.hospitalapp.repository.PatientRepository;
import ma.enset.hospitalapp.security.SecurityConfig;
import ma.enset.hospitalapp.security.entities.AppRole;
import ma.enset.hospitalapp.security.entities.AppUser;
import ma.enset.hospitalapp.security.repositories.AppRoleRepository;
import ma.enset.hospitalapp.security.repositories.AppUserRepository;
import ma.enset.hospitalapp.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class HospitalAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,42));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,98));
            patientRepository.save(new Patient(null,"Yassine",new Date(),true,342));
            patientRepository.save(new Patient(null,"Laila",new Date(),false,123));
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountService accountService){
        return args -> {
          accountService.addUser("ahmedenouri","1234","ahmedennouri@gmail.com","1234");
          accountService.addUser("wafaemoujib","1234","wafaemoujib@gmail.com","1234");
          accountService.addRole("USER");
          accountService.addRole("ADMIN");

          accountService.addRoleToUser("ahmedenouri","ADMIN");
          accountService.addRoleToUser("ahmedenouri","USER");
          accountService.addRoleToUser("wafaemoujib","USER");
        };
    }
}
