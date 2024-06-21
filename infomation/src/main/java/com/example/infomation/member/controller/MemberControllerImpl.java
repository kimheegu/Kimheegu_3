package com.example.infomation.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.infomation.member.service.MemberService;
import com.example.infomation.member.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired 
	private MemberVO memberVO;
	
	@Override
	@GetMapping("/member/listMember.do")
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView("/member/listMembers");
		
		mav.addObject("memberList",membersList);
		return mav;
	}

	@Override
	@PostMapping("/member/addMember.do")
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}

	@Override
	@GetMapping("/member/removeMember.do")
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}

	@Override
	@PostMapping("/member/login.do")
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			

				mav.setViewName("redirect:/member/listMember.do");
		}
		return mav;
	}

	@Override
	@GetMapping("/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.setAttribute("isLogOn", false);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMember.do");
		return mav;
	}
	
	@GetMapping("/member/*Form.do")
	private ModelAndView form(@RequestParam(value="result", required=false) String result,
			@RequestParam(value="action", required=false) String action, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@PostMapping("/member/modmember.do")
	public ModelAndView modMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.updateMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@GetMapping({"/","/main.do"})
	private ModelAndView main(HttpServletRequest request,HttpServletResponse response) {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@GetMapping("member/islogin.do")
	private ModelAndView islogin(@ModelAttribute("member") MemberVO member,HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		MemberVO member1 = (MemberVO)session.getAttribute("member");
		
		if(member1 != null ) {
			mav = new ModelAndView("/member/modMemberForm");
		}else {
			mav = new ModelAndView("redirect:/member/loginForm.do");
		}
		return mav;
	}
	
}
