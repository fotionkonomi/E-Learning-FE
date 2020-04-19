package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.elearning.fe.common.enums.GenderEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserFeModel extends BaseClassModel {

	@Size(max = 50)
	@NotEmpty
	private String firstName;
	
	@Size(max = 50)
	@NotEmpty
	private String lastName;
	
	@Size(max = 50)
	@NotEmpty
	private String username;
	
	@Size(max = 255)
	@NotEmpty
	private String password;
	
	private GenderEnum gender;
	
	@Size(max = 100)
	@NotEmpty
	private String email;
	
	private Date dateOfBirth;
	
	private Date dateRegistered;
	
	private FacultyFeModel faculty;
	
	private RoleFeModel role;
	
	private Set<CourseFeModel> myStudentCourses = new HashSet<>();
	
	private List<CourseFeModel> myProfessorCourses = new ArrayList<>();

	private Set<AnswerFeModel> myAnswers = new HashSet<>();
}
