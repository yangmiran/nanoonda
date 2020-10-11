package diary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import diary.model.service.DiaryService;
import diary.model.vo.Diary;
import pro.model.service.ProService;

/**
 * Servlet implementation class TempUpdateServlet
 */
@WebServlet("/tupdate")
public class TempUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업로드할 파일의 용량 제한
		int maxSize = 1024 * 1024 * 10;
		
		//업로드되는 파일의 저장 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/resources/dupimages");
		
		//MultipartRequest로 변환
		MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String title =mrequest.getParameter("title");
		String content = mrequest.getParameter("content");
		
		//금지어 확인
		boolean checkResult = new ProService().proCheck(title, content);
		
		if(checkResult == false) { //금지어 통과시
			Diary diary = new Diary();
			diary.setDiaryTitle(title);
			diary.setDiaryContent(content);
			
			int diaryNo = Integer.parseInt(mrequest.getParameter("dno"));
			int currentPage = Integer.parseInt(mrequest.getParameter("page"));
			diary.setDiaryNo(diaryNo);
			
			//이전 등록 파일 삭제 여부 값 추출
			String deleteFlag = mrequest.getParameter("delflag");

			//이전 등록 파일명 추출
			String diaryOriginfile = mrequest.getParameter("ofile");
			String diaryRenamefile = mrequest.getParameter("rfile");
			
			//새로운 첨부 파일명 추출하기
			String diaryUpdatefile = mrequest.getFilesystemName("upfile");
			
			//원래 파일과 새로 업로드된 파일의 이름과 용량이 동일하면, 원래 이름 그대로 객체에 기록.
			//업로드된 파일의 File 객체 만들기
			File newOriginFile = new File(savePath + "/" + diaryUpdatefile);
			//이전 저장된 파일의 File 객체 만들기
			File originFile = new File(savePath + "/" + diaryRenamefile);
			

			//"년월일시분초.확장자" 형식으로 변경
			if (diaryUpdatefile != null) {
				diary.setDiaryOriginfile(diaryUpdatefile);
				 
				//새로운 첨부파일이 있다면, 이름 바꾸기 실행			
				//바꿀 파일명에 대한 포맷 문자열 만들기 : 년월일시분초 형식
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				
				//바꿀 파일명 만들기
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				
				//업로드 된 파일의 확장자를 추출해서, 새 파일명에 붙여줌.
				renameFileName += "." + diaryUpdatefile.substring(diaryUpdatefile.lastIndexOf(".") + 1);
				
				//원본 파일명 rename 처리를 위해서 File 객체 만들기
				File renameFile = new File(savePath + "\\" + renameFileName);
				
				// 이름 바꾸기 실행함
				if (!newOriginFile.renameTo(renameFile)) {
					//renameTo메소드가 실패(false)한 경우에 직접 바꾸기함
					//원본 파일 내용 읽어서 복사본에 기록하고, 완료되면 원본 파일 삭제
					FileInputStream fin = new FileInputStream(originFile);
					FileOutputStream fout = new FileOutputStream(renameFile);
					int data = -1;
					byte[] buffer = new byte[1024];

					while ((data = fin.read(buffer, 0, buffer.length)) != -1) {
						fout.write(buffer, 0, buffer.length);
					}
					fin.close();
					fout.close();
					newOriginFile.delete(); //새로 업로드된 원본 파일 삭제
				} //직접 이름바꾸기 종료
				diary.setDiaryRenamefile(renameFileName);
				
				//이전 첨부파일이 있었다면
				if(diaryOriginfile != null) {
					originFile.delete();
				}
			} else if(diaryOriginfile != null && deleteFlag != null && deleteFlag.equals("yes")) {
				//원래 첨부파일이 있었는데 파일삭제가 선택된 경우
				diary.setDiaryOriginfile(null);
				diary.setDiaryRenamefile(null);
				//폴더에 저장된 파일도 삭제함
				originFile.delete();
			} else if(diaryOriginfile != null && (diaryUpdatefile == null || diaryOriginfile.equals(diaryUpdatefile) && newOriginFile.length() == originFile.length())) {
				//원래 첨부파일이 있었는데 변경되지 않은 경우
				diary.setDiaryOriginfile(diaryOriginfile);
				diary.setDiaryRenamefile(diaryRenamefile);
			}

			int result = new DiaryService().updateTempDiary(diary);

			if (result > 0) {
				response.sendRedirect("dlist?page=" + currentPage);
			} else {
				response.setContentType("text/html; charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.println("<script>alert('임시저장된 일기 등록에 실패하였습니다.'); history.go(-1);</script>");
		        out.flush();
		        out.close();
			}
		} else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('금지어가 포함된 게시글은 등록할 수 없습니다.'); history.go(-1);</script>");
	        out.flush();
	        out.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
