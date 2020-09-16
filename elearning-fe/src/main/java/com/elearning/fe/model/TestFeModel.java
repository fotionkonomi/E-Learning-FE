package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TestFeModel extends BaseClassModel {

	@Size(max = 50)
	@NotEmpty
	private String name;
	
	@NotNull
	private CourseFeModel course;
	
	private List<QuestionFeModel> questions = new ArrayList<>();
	
}
