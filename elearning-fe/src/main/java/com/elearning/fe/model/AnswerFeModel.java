package com.elearning.fe.model;

import java.util.HashSet;
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
public class AnswerFeModel extends BaseClassModel {

	@Size(max = 65535)
	@NotEmpty
	private String answer;
	
	private Boolean correct;
	
	private QuestionFeModel question;
	
	private Set<UserFeModel> usersThatChoseThisAnswer = new HashSet<>();
	
}
