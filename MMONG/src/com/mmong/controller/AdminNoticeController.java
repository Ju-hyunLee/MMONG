package com.mmong.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mmong.service.AdministratorNoticeService;
import com.mmong.validation.AdministratorNoticeValidator;
import com.mmong.vo.AdministratorNotice;

@Controller
@RequestMapping("/adminNotice/")
public class AdminNoticeController {

	@Autowired
	private AdministratorNoticeService adminNoticeService;
	
	
	
	
	
	
	//관리자 공지사항 페이징
	@RequestMapping("selectAdminNoticeList")
	public String selectAdminNoticeList(@RequestParam(value="page", defaultValue="1")int page, 
																@RequestParam (value="option", defaultValue="1")String option, 
																@RequestParam  (value="keyword", defaultValue="1")String keyword, 
																ModelMap map) {
				
				
			Map<String, Object> pagingMap =null;
			
			//2. 비지니스 로직 처리 - Model 호춛
			
			if(option.equals("1")){ // option 선택을 안했을 때
				pagingMap = adminNoticeService.selectAdminNoticeListPaging(page); 
			}else{ // option 선택했을 때
				pagingMap=adminNoticeService.selectOptionNoitceList(page,option,keyword);
			}
			
			//3. 응답 - View 호출
			map.addAttribute("adminNoticeList", pagingMap.get("adminNoticeList"));
			map.addAttribute("pageBean", pagingMap.get("pageBean"));

			return "adminNotice/selectAdminNoticeList.tiles";
	}
	
	
	//관리자 공지사항 등록
	@RequestMapping("insertAdminNotice")
	public ModelAndView insertAdminNotice (@ModelAttribute AdministratorNotice adminNotice, BindingResult errors){
		System.out.println(adminNotice);
		// 요청 파라미터 검증
		AdministratorNoticeValidator validator = new AdministratorNoticeValidator();
		validator.validate(adminNotice, errors);
		if(errors.hasErrors()){
			return  new ModelAndView("adminNotice/insertAdminNotice_form.tiles");
		}
		
		
		adminNotice.setAdminDate(new Date());
		adminNoticeService.insertAdminNotice(adminNotice);
		int adminNoticeNo = adminNotice.getNo();
		return new ModelAndView("redirect:/adminNotice/viewAdminNotice.do","adminNoticeNo", adminNoticeNo);
	}
	
	//관리자 공지사항 등록 - ajax(자바스크립트 해제하면 컨트롤러도 작동 안하므로 전송을 못해서 밸리데이터 굳이 필요 없음
	@RequestMapping("insertAdminNoticeAjax")
	@ResponseBody
	public int insertAdminNoticeAjax (@ModelAttribute AdministratorNotice adminNotice){
		adminNotice.setAdminDate(new Date());
		adminNoticeService.insertAdminNotice(adminNotice);
		int adminNoticeNo = adminNotice.getNo();
		System.out.println(adminNoticeNo);
		return adminNoticeNo;
	}
	
	
	//관리자 공지사항 삭제
	@RequestMapping("deleteAdminNotice")
	public ModelAndView deleteAdminNotice(@RequestParam int adminNoticeNo){
			adminNoticeService.deleteAdminNotice(adminNoticeNo);
		List<AdministratorNotice> adminNoticeList = null;
		adminNoticeList = adminNoticeService.selectAdminNoticeList();
		return new ModelAndView("adminNotice/selectAdminNoticeList.tiles","adminNoticeList", adminNoticeList);
	}
		
	
	//게시물 하나 조회에서 수정 폼으로 이동
	@RequestMapping("updateAdminNotice_form")
	public ModelAndView updateAdminNoticeForm(@RequestParam int adminNoticeNo){
		AdministratorNotice adNotice = null;
			adNotice = adminNoticeService.viewAdminNoticeByNo(adminNoticeNo);
		return new ModelAndView("adminNotice/updateAdminNotice_form.tiles","adminNotice", adNotice);
	}
	
	
	//관리자 공지사항 수정 폼(updateAdminNotice_form.jsp)에서 수정 완료후 다시 하나 조회(view_notice.jsp)로 이동
	@RequestMapping("updateAdminNotice")
	public ModelAndView updateAdminNotice(@ModelAttribute AdministratorNotice newAdminNotice, BindingResult errors){
		// 요청 파라미터 검증
		AdministratorNoticeValidator validator = new AdministratorNoticeValidator();
		validator.validate(newAdminNotice, errors);
		if(errors.hasErrors()){
			return  new ModelAndView("redirect:/adminNotice/updateAdminNotice_form.do?adminNoticeNo="+newAdminNotice.getNo());
		}

		adminNoticeService.updateAdminNotice(newAdminNotice);
		int newAdminNoticeNo = newAdminNotice.getNo();
		return new ModelAndView("redirect:/adminNotice/viewAdminNotice.do","adminNoticeNo",newAdminNoticeNo);
	}

	
	//관리자 공지사항 글 하나 조회
	@RequestMapping("viewAdminNotice")
	public ModelAndView viewAdminNotice(@RequestParam int adminNoticeNo){
		AdministratorNotice adNo = adminNoticeService.viewAdminNoticeByNo(adminNoticeNo);
		return new ModelAndView("adminNotice/view_notice.tiles", "adminNotice", adNo);
	}
	
	//공지사항 검색 (제목 or 내용)
	@RequestMapping("selectAdminNoticeByKeyword")
	public ModelAndView selectAdminNoticeByKeyword(@RequestParam("option")String option, @RequestParam("keyword")String keyword){
		List<AdministratorNotice> adminNoticeListByKeyword = null;
		
		if(option.equals("제목")){
			adminNoticeListByKeyword = adminNoticeService.selectAdminNoticeListByTitle(keyword);
		}else if(option.equals("내용")){
			adminNoticeListByKeyword = adminNoticeService.selectAdminNoticeListByContent(keyword);
		}
		return new ModelAndView("adminNotice/selectAdminNoticeList.tiles","adminNoticeList", adminNoticeListByKeyword);
	}
	
}
