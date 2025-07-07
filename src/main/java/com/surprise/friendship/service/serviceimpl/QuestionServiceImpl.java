package com.surprise.friendship.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surprise.friendship.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	private final List<String> questions = List.of("Where did our friendship truly begin?",
			"What's that one song that always reminds us of each other?",
			"Name one place we’ve been together that means a lot to us.",
			"What’s the funniest thing we’ve done that only we understand?",
			"What’s one thing I always say or do that’s 'so me'?",
			"If we could rewind to one day in our friendship, which one would you choose?",
			"If we were lost somewhere, who would be the leader and why?");

	@Override
	public String getQuestion(int id) {
		if (id < 1 || id > 7) {
			return "Invalid question ID. Please use 1 to 7.";
		}
		return questions.get(id - 1);
	}

	@Override
	public List<String> getAllQuestions() {
		return questions;
	}

}
