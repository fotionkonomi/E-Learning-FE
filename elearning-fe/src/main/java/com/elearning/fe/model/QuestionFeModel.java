package com.elearning.fe.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.elearning.fe.common.enums.QuestionDifficulty;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuestionFeModel extends BaseClassModel {

	@Size(max = 65535)
	@NotEmpty
	private String question;
	
	private Boolean correct;
	
	private QuestionDifficulty difficulty;
	
	private Long numberOfPoints;
	
	private List<AnswerFeModel> answers = new ArrayList<>();

	private Set<TestFeModel> tests = new HashSet<>();
}
