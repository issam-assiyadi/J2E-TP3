package ma.enset.tp3;

import ma.enset.tp3.entities.Patient;
import ma.enset.tp3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp3Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Patient patient = Patient.builder()
                .name("name1")
                .dateNaissance(new Date())
                .score(123)
                .malade(true)
                .build();
        Patient patient1 = Patient.builder()
                .name("name2")
                .dateNaissance(new Date())
                .score(12)
                .malade(false)
                .build();

//        // save patients
//        patientRepository.save(patient1);
//        patientRepository.save(patient);





    }
}
