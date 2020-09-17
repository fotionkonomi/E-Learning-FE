package com.elearning.fe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elearning.fe.model.CourseFeModel;
import com.elearning.fe.model.QuestionFeModel;
import com.elearning.fe.model.TestFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController extends AbstractController<TestFeModel> {

	private List<QuestionFeModel> questions = new ArrayList<>();
	
	public TestController() {
		super(TestFeModel.class);
	}

	@ModelAttribute(name = "test")
	public TestFeModel test() {
		return new TestFeModel();
	}
	
	@ModelAttribute("questions")
	public List<QuestionFeModel> questionsModel() {
		return questions;
	}
	
	@PostMapping("/question")
	@ResponseBody
	public QuestionFeModel addQuestion(@ModelAttribute("questions") List<QuestionFeModel> questions, @RequestBody QuestionFeModel question) {
		questions.add(question);
		return question;
	}
	
	@PutMapping("/question/{questionCount}")
	@ResponseBody
	public QuestionFeModel editQuestion(@ModelAttribute("questions") List<QuestionFeModel> questions, @RequestBody QuestionFeModel question, @PathVariable("questionCount") int questionCount) {
		QuestionFeModel questionToEdit = questions.get(questionCount);
		questionToEdit.setQuestion(question.getQuestion());
		questionToEdit.setDifficulty(question.getDifficulty());
		questionToEdit.setNumberOfPoints(questionToEdit.getNumberOfPoints());
		questions.remove(questionCount);
		questions.add(questionCount, questionToEdit);
		return questionToEdit;
	}
	
	@GetMapping("/questions")
	@ResponseBody
	public List<QuestionFeModel> getQuestions() {
		return questions;
	}

	@ModelAttribute(name = "courses")
	public Collection<CourseFeModel> courses() {
		HttpEntity<CourseFeModel[]> courses = rest.postExchange("http://localhost:8181/api/course/user",
				new HttpEntity<Long>(authenticationFacade.getAuthenticatedUser().getId()), CourseFeModel[].class);
		return Arrays.asList(courses.getBody());
	}

	@PostMapping("/add")
	public String addTest(@Valid @ModelAttribute("test") TestFeModel testFeModel, Errors errors) {
		testFeModel.setQuestions(questions);
		return add(testFeModel, errors);
	}

}
