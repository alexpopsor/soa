package com.example.gradesservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
public class GradesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradesServiceApplication.class, args);
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

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Grade {

	public Grade(@NonNull Long student_id, @NonNull String course_name, @NonNull Integer grade) {
		this.student_id = student_id;
		this.course_name = course_name;
		this.grade = grade;
	}

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private Long student_id;

	@NonNull
	private String course_name;

	@NonNull
	private Integer grade;
}

@RepositoryRestResource
interface GradesRepository extends JpaRepository<Grade, Long> {
}

@RestController
class GradesController {
	@Autowired
	GradesRepository gradesRepository;

	@GetMapping("/grades")
	public ArrayList<Grade> getGrades() {
		return new ArrayList<>(gradesRepository.findAll());
	}

	@GetMapping("/grades/{student_id}")
	public List<Grade> getGradeByStudent(@PathVariable Long student_id) {
		return gradesRepository.findAll().stream().filter(grade -> grade.getStudent_id().equals(student_id)).collect(Collectors.toList());
	}

	@PostMapping("/grades")
	public Grade postGrade(@RequestBody Grade grade) {
		gradesRepository.save(grade);
		return grade;
	}
}