package com.elearning.fe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elearning.fe.model.AnswerFeModel;
import com.elearning.fe.model.CourseFeModel;
import com.elearning.fe.model.QuestionFeModel;
import com.elearning.fe.model.TestFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController extends AbstractController<TestFeModel> {

	private List<QuestionFeModel> questions;
	private List<AnswerFeModel> answers;

	public TestController() {
		super(TestFeModel.class);
		questions = new ArrayList<>();
		answers = new ArrayList<>();
	}

	@ModelAttribute(name = "test")
	public TestFeModel test() {
		return new TestFeModel();
	}

	@ModelAttribute("questions")
	public List<QuestionFeModel> questionsModel() {
		return questions;
	}

	@ModelAttribute("answers")
	public List<AnswerFeModel> answersModel() {
		return answers;
	}

	@PostMapping("/answer")
	@ResponseBody
	public AnswerFeModel addAnswer(@RequestParam("questionCount") int questionCount,
			@RequestBody AnswerFeModel answer) {
		answer.setQuestion(questions.get(questionCount));
		answers.add(answer);
		return answer;
	}

	@PostMapping("/question")
	@ResponseBody
	public QuestionFeModel addQuestion(@RequestBody QuestionFeModel question) {
		questions.add(question);
		return question;
	}

	@PutMapping("/question/{questionCount}")
	@ResponseBody
	public QuestionFeModel editQuestion(@RequestBody QuestionFeModel question,
			@PathVariable("questionCount") int questionCount) {
		QuestionFeModel questionToEdit = questions.get(questionCount);
		questionToEdit.setQuestion(question.getQuestion());
		questionToEdit.setDifficulty(question.getDifficulty());
		questionToEdit.setNumberOfPoints(questionToEdit.getNumberOfPoints());
		questions.remove(questionCount);
		questions.add(questionCount, questionToEdit);
		return questionToEdit;
	}
	
	@PutMapping("/answer/{questionCount}/{answerCount}")
	@ResponseBody
	public AnswerFeModel editAnswer(@RequestBody AnswerFeModel answer, @PathVariable("questionCount") int questionCount, @PathVariable("answerCount") int answerCount) {
		AnswerFeModel answerToEdit = getAnswersOfAQuestion(questionCount).get(answerCount);
		answers.remove(answerToEdit);
		answerToEdit.setAnswer(answer.getAnswer());
		answerToEdit.setCorrect(answer.getCorrect());
		answers.add(answerCount, answerToEdit);
		return answerToEdit;
	}
	
	@DeleteMapping("/answer/delete/{questionCount}/{answerCount}")
	@ResponseBody
	public void deleteAnswer(@PathVariable("questionCount") int questionCount, @PathVariable("answerCount") int answerCount) {
		AnswerFeModel answerToRemove = getAnswersOfAQuestion(questionCount).get(answerCount);
		answers.remove(answerToRemove);
	}
	
	@DeleteMapping("/question/delete/{questionCount}") 
	@ResponseBody
	public void deleteQuestion(@PathVariable("questionCount") int questionCount) {
		log.info("Quesitons Before:" + questions);
		log.info("Answers Before: " + answers);
		List<AnswerFeModel> answersToRemove = getAnswersOfAQuestion(questionCount);
		if(!answersToRemove.isEmpty()) {
			answers.removeAll(answersToRemove);
		}
		questions.remove(questionCount);
		
		log.info("Quesitons After:" + questions);
		log.info("Answers After: " + answers);
	}

	@GetMapping("/questions")
	@ResponseBody
	public List<QuestionFeModel> getQuestions() {
		return questions;
	}

	@GetMapping("/answers/{questionCount}")
	@ResponseBody
	public List<AnswerFeModel> getAnswersOfAQuestion(@PathVariable("questionCount") int questionCount) {
		return answers.stream().filter(answer -> answer.getQuestion().equals(questions.get(questionCount)))
				.collect(Collectors.toList());
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
