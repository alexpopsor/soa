package com.example.studentservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Configuration
	static class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http
					.authorizeRequests().anyRequest().authenticated()
					.and()
					.oauth2ResourceServer().jwt();
			// @formatter:on
		}
	}

	@Bean
	ApplicationRunner init(StudentRepository repository) {
		return args -> {
			Stream.of("Alex Popsor", "John Doe").forEach(name -> {
				repository.save(new Student(name));
			});
			repository.findAll().forEach(System.out::println);
		};
	}
}

@Data
@NoArgsConstructor
@Entity
class Student {

	public Student(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String name;
}

@RepositoryRestResource
interface StudentRepository extends JpaRepository<Student, Long> {
}

@RestController
class StudentController {
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public ArrayList<Student> getStudents() {
		return new ArrayList<>(studentRepository.findAll());
	}

	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return student;
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentRepository.deleteById(id);
	}
}