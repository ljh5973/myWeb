package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Autowired
	@Qualifier("freeService")
	private FreeBoardService freeService;
	
	//리스트화면
	@RequestMapping("/freeList")
	public String freeList(Model model) {
		
		ArrayList<FreeBoardVO>list =  freeService.getList();
		
		model.addAttribute("list",list); //화면에 전달(여러값이라면 맵으로 가능)
		
		System.out.println(list.toString());
		
		return "freeBoard/freeList";
	}
	
	
	//등록화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
//	//상세화면
//	
//	@RequestMapping("/freeDetail")
//	public String freeDetail(@RequestParam("bno") int bno,
//							Model model) {
//		
//		//메서드 이름 - getDetail()
//		//sql문을 이용해서 FreeBoardVO에 결과값을 반환 받습니다.
//		//화면에서 사용할 수 있도록 boardVO이름으로 model을 전달하고, 화면에 처리
//		 FreeBoardVO boardvo = freeService.getDetail(bno);
//	      model.addAttribute("boardvo", boardvo);
//	      
//	      return "freeBoard/freeDetail";
//	
//	}
//	
//	//변경화면
//	
//	@RequestMapping("/freeModify")
//	public String freeModify(@RequestParam("bno") int bno, Model model) {
//		
//		FreeBoardVO boardvo = freeService.getDetail(bno);
//	      model.addAttribute("boardvo", boardvo);
//		
//		return "freeBoard/freeModify";
//	}
	
	//상세화면과 변경화면은 내용이 동일하기 때문에 하나로 묶어서 사용
	@RequestMapping({"/freeDetail","/freeModify"})
	public void getDetail(@RequestParam("bno") int bno, Model model) {
		
		FreeBoardVO boardVo = freeService.getDetail(bno);
	      model.addAttribute("boardVO", boardVo);
		
	}
	
	
	
	//등록 요청
	@RequestMapping("/registForm")
	public String registForm(FreeBoardVO vo,
							RedirectAttributes RA) {
		
		int result = freeService.regist(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg","등록 처리 되었습니다.");
		}else {
			RA.addFlashAttribute("msg","등록에 실패했습니다. 다시 시도하세요");
		}
		
		
		return "redirect:/freeBoard/freeList"; //다시컨트롤러를 태운
	}
	
	@RequestMapping({"/freeUpdate"})
	public String freeUpdate(FreeBoardVO vo,RedirectAttributes RA) {
//		/*
//		 * 
//		 * 1. form에서 넘오는 값을 받습니다.
//		 * 2. update()를 이용해서 게시글을 수정처리합니다.
//		 * 3. update()메서드는 성공 or 실패의 결과를 받아옵니다.
//		 * 4. list화면으로 msg담아서 이동
//		 */
//		
		int result = freeService.Update(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg","수정 처리 되었습니다.");
		}else {
			RA.addFlashAttribute("msg","수정에 실패했습니다. 다시 시도하세요");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	//글삭제
	
	@RequestMapping("/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno,
							RedirectAttributes RA) {
		
		int result=freeService.delete(bno);
		
		if(result==1) {
			RA.addFlashAttribute("msg", "삭제 되었습니다.");
		}else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다. 다시 시도해주세요.");
		}
		
		return "redirect:/freeBoard/freeList";
	}
}
