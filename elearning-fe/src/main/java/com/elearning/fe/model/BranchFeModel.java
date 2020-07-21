package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.elearning.fe.util.constraints.annotation.NotUnselected;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BranchFeModel extends BaseClassModel {

	@Size(max = 100)
	@NotEmpty
	private String name;
	
	@NotUnselected
	private FacultyFeModel faculty;
	
	private List<UserFeModel> students = new ArrayList<>();
}
