package com.chatting.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TestController {
	
	@PostMapping("/test")
	public Map<String, String> hello(@RequestBody Map<String, String> requestMap) {
		System.out.println(requestMap.get("name"));
		Map<String, String> map = new HashMap<>();
		map.put("hello", "world");
		return map;
	}
	@GetMapping("/index")
	public ModelAndView indexPage(ModelAndView mView) {
		mView.setViewName("/index");
		return mView;
	}
	@GetMapping("/sample")
	public ModelAndView samplePage(ModelAndView mView) {
		mView.setViewName("/test");
		return mView;
	}
}
