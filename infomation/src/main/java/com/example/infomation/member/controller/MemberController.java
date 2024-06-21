package com.example.infomation.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.infomation.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberController {

	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView addMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView removeMember(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView login(MemberVO memberVO, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView modMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}