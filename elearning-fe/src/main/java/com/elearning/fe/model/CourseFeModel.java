package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseFeModel extends BaseClassModel {

	@Size(max = 100)
	@NotEmpty
	private String name;
	
	private UserFeModel professor;
	
	private List<TestFeModel> tests = new ArrayList<>();
	
	private Set<UserFeModel> students = new HashSet<>();
}
