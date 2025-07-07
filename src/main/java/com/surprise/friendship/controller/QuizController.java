package com.surprise.friendship.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.surprise.friendship.service.EmailService;
import com.surprise.friendship.service.QuestionService;
import com.surprise.friendship.utitlity.PdfGenerator;

import jakarta.servlet.http.HttpSession;

@Controller
public class QuizController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/")
    public String home() {
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String start() {
        return "start";
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") int id, Model model) {
        String question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("id", id);
        return "question";
    }

    @SuppressWarnings("unchecked")
	@GetMapping("/result")
    public String showResult(HttpSession session, Model model) {
        Map<Integer, String> answers = (Map<Integer, String>) session.getAttribute("answers");
        model.addAttribute("answers", answers);
        
        try {
            byte[] pdfData = PdfGenerator.generateQuizPdf(answers);
            emailService.sendPdfEmail("your_email@gmail.com", pdfData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "result";
    }

    
    @PostMapping("/submitAnswer")
    public String submitAnswer(@RequestParam("id") int id, @RequestParam("answer") String answer, HttpSession session) {
        @SuppressWarnings("unchecked")
		Map<Integer, String> answers = (Map<Integer, String>) session.getAttribute("answers");
        if (answers == null) {
            answers = new HashMap<>();
        }
        answers.put(id, answer);
        session.setAttribute("answers", answers);

        if (id < 7) {
            return "redirect:/question/" + (id + 1);
        } else {
            return "redirect:/result";
        }
    }

}
