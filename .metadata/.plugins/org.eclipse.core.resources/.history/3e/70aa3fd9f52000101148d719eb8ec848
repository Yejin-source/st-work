package com.example.fileupload.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fileupload.dto.BoardForm;
import com.example.fileupload.entity.Board;
import com.example.fileupload.entity.BoardMapping;
import com.example.fileupload.entity.Boardfile;
import com.example.fileupload.repository.BoardRepository;
import com.example.fileupload.repository.BoardfileRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	BoardRepository boardRepository;
	@Autowired 
	BoardfileRepository boardfileRepository;
    
	// 게시글 입력 폼
	@GetMapping("/addBoard")
	public String addBoard() {
		return "addBoard";
	}
	
	// 게시글 입력 액션
	@PostMapping("/addBoard")
	@Transactional // 하나라도 만족하지 않는 코드가 있을 때 다 취소됨 (롤백)
	public String addBoard(BoardForm boardForm, RedirectAttributes rda) {
		log.debug(boardForm.toString());
		// 이슈: 파일을 첨부하지 않아도 fileSize는 1
		log.debug("MultipartFile Size: " + boardForm.getFileList().size());
		
		
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setPw(boardForm.getPw());
		boardRepository.save(board); // board 저장
		int bno = board.getBno(); // board insert 후 bno 변경되었는지
		log.debug("bno: " + bno);
		
		
		// 파일 분리
		List<MultipartFile> fileList = boardForm.getFileList();
		long firstFileSize = fileList.get(0).getSize();
		log.debug("firstFileSize: " + firstFileSize);
		
		// 이슈: 파일을 첨부하지 않아도 fileSize는 1
		if(firstFileSize > 0) { // 첫 번째 파일 사이즈가 0 이상 -> 첨부된 파일 존재
			
			// 업로드 파일 유효성 검증 코드 구현
			for(MultipartFile f : fileList) {
				if(f.getContentType().equals("application/octet-stream") || f.getSize() > 1024*1024*10) { 
														// 1Kbyte = 1024byte(2^10), 1Mbyte = 1024Kbyte
					log.debug("10MB이상의 파일은 업로드할 수 없습니다.");
					return "redirect:/addBoard"; // Msg 추가
				}
			}
			
			// 파일 업로드 진행 코드
			for(MultipartFile f : fileList) {
				log.debug("파일타입: " + f.getContentType());
				// log.debug("파일이름: " + f.getName()); // input name
				log.debug("원본이름: " + f.getOriginalFilename());
				log.debug("파일용량: " + f.getSize());						
				
				
				// 확장자 추출							// 마지막 점 다음 위치부터 자르기
				String ext = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")+1); 
				log.debug("확장자: " + ext);
				String fname = UUID.randomUUID().toString().replace("-", "");
				log.debug("저장 파일 이름: " + fname);
				
				
				File emptyFile = new File("c:/project/upload/" + fname + "." + ext);
				// f의 byte -> emptyFile 복사
				try {
					f.transferTo(emptyFile);
				} catch (Exception e) {
					log.error("파일 저장 실패");
					e.printStackTrace();
				}
				
				
				// boardfile테이블에도 파일정보 저장
				Boardfile boardfile = new Boardfile();
				boardfile.setBno(board.getBno());
				boardfile.setFext(ext);
				boardfile.setFname(fname);
				boardfile.setForiginname(f.getOriginalFilename());
				boardfile.setFsize(f.getSize());
				boardfile.setFtype(f.getContentType());
				boardfileRepository.save(boardfile);
				
				log.debug("게시글 작성 성공");
				rda.addFlashAttribute("msg", "게시글이 성공적으로 등록되었습니다.");
			}
		}
		return "redirect:/boardList";
	}
	
	
	// 게시판 목록
	@GetMapping({"/", "/boardList"})
	public String boardList(Model model, @RequestParam(value = "currentPage", defaultValue = "0") int currentPage
										, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		
		// 페이징
		// sort : bno DESC
		// PageRequest : 0, 10
		// Page<BoardMapping>
		
		Sort sort = Sort.by("bno").descending(); // 내림차순
		// pageRequest.of(현재 페이지, 페이지당 데이터 수, 정렬 방식)
		PageRequest pageable = PageRequest.of(currentPage, rowPerPage, sort); 
		
		Page<BoardMapping> list = boardRepository.findAllBy(pageable);
	
		// 정보 저장
		model.addAttribute("list", list);
		model.addAttribute("prePage", list.getNumber() - 1);
		model.addAttribute("nextPage",list.getNumber() + 1);
		return "boardList";
	}
	
	
	// 상세보기
	@GetMapping("/boardOne")
	public String boardOne(Model model, @RequestParam(value = "bno") int bno) {
		BoardMapping boardMapping = boardRepository.findByBno(bno);
		
		List<Boardfile> fileList = boardfileRepository.findByBno(bno);
		log.debug("size: " + fileList.size());
		
		model.addAttribute("boardMapping", boardMapping);
		model.addAttribute("fileList", fileList);
		return "boardOne";
	}
	
	
	// 게시글 수정
	@GetMapping("/modifyBoard") // Model model: 컨트롤러 -> 뷰로 데이터를 넘겨주는 통로
	public String modifiyBoard(Model model, @RequestParam(value = "bno") int bno) {
		
		// 템플릿에서 boardMapping으로 출력해도, 컨트롤러에서 반드시 BoardMapping으로 받아야 하는 건 아님
		Board board = boardRepository.findById(bno).orElse(null); // null 허용 가능하게 변경
		model.addAttribute("board", board);
		return "modifyBoard";
	}
	
	// 게시글 수정 기능
	@PostMapping("/update") // boardForm: 사용자가 입력하는 필드만 포함, 보안상 안전하기 때문에 사용
	public String update(BoardForm boardForm, RedirectAttributes rda, @RequestParam(value = "pw") String pw) { 
		
		// DB에서 게시판 정보 가져오기
		Board board = boardRepository.findById(boardForm.getBno()).orElse(null);
		
		// 게시판 정보가 없거나 비밀번호가 일치하지 않는 경우
		if(board == null || !board.getPw().equals(pw)) {
			log.debug("게시글 수정 실패");
			rda.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/modifyBoard?bno="+board.getBno(); // 수정 및 삭제에 실패한 경우에도 리다이렉트
		}
		
		// 게시글 수정
		board.setTitle(boardForm.getTitle());
		board.setPw(boardForm.getPw());
		boardRepository.save(board);
		
		log.debug("게시글 수정 성공");
		rda.addFlashAttribute("msg", "게시글이 수정되었습니다.");
 		return "redirect:/boardOne?bno="+board.getBno();
	}
	
	
	// 게시글 삭제
	@GetMapping("/removeBoard")
	public String removeBoard(Model model, @RequestParam(value = "bno") int bno) {
		
		// DB에서 게시판 정보 가져오기
		Board board = boardRepository.findById(bno).orElse(null);
		model.addAttribute("board", board);
		return "removeBoard";
	}
	
	// 게시글 삭제 기능
	@PostMapping("/delete")
	public String delete(BoardForm boardForm, RedirectAttributes rda, @RequestParam(value = "pw") String pw) {
		
		// DB에서 게시판 정보 가져오기
		Board board = boardRepository.findById(boardForm.getBno()).orElse(null);
		
		
		// 게시판 정보가 없거나 비밀번호가 일치하지 않는 경우
		if(board == null || !board.getPw().equals(pw)) {
			log.debug("게시글 삭제 실패");
			rda.addFlashAttribute("msg", "게시글이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			return "redirect:/boardOne?bno="+board.getBno();
		}
		
		
		// 첨부된 파일이 있는 경우 -> Boardfile에서 bno(게시판 번호)로 파일이 있는지 확인 필요
		List<Boardfile> fileList = boardfileRepository.findByBno(board.getBno());
		if(!fileList.isEmpty() || fileList != null) {
			log.debug("게시글 삭제 실패");
			rda.addFlashAttribute("msg", "첨부파일이 있을 경우 게시글을 삭제할 수 없습니다.");
			return "redirect:/boardOne?bno="+board.getBno();
		}
		
		// 게시글 삭제
		boardRepository.delete(board);
		log.debug("게시글 삭제 성공");
		rda.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
		return "redirect:/boardList";
	}
}

