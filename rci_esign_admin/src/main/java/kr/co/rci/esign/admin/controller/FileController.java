/**
 *  공통 파일 컨트롤러
 *
 * @author Think-Tree Inc.
 * @Version 1.0.0
 */
package kr.co.rci.esign.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.rci.esign.admin.constant.Url;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.FileInfo;
import kr.co.rci.esign.admin.service.FileService;
import kr.co.rci.esign.admin.view.AttachDownloadView;


@Controller
public class FileController extends CoTopComponent {

	@Autowired FileService fileService;

	@PostMapping(value={Url.COMMON.FILE_UPLOAD})
	@ResponseBody
	public ResponseEntity<Object> fileUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile uploadFile) {

    	Map<String, Object> resultMap = new HashMap<>();
        FileInfo _file;
        try {
        	_file = fileService.uploadFile(req);
            if(_file == null || isEmpty(_file.getFileNo())) {
            	resultMap.put("result", "FAIL");
            } else {
            	resultMap.put("result", "SUCC");
            	resultMap.put("fileInfo", _file);
            }

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}



        return makeResponseEntity(resultMap);
    }



	/**
	 * 이미지보기
	 * @param
	 * @param
	 * @return
	 */
	@GetMapping(value={"/imgShow"})
	public ModelAndView getApplyImage( HttpServletRequest req, HttpServletResponse res) {

		//String filePath = req.getParameter("path");
		//String fileType= req.getParameter("type");
		String fileIdx= req.getParameter("fId");
		//return getApplicationImage(fileIdx, req.getServletContext());

		ModelAndView mav = new ModelAndView();
		if(StringUtils.isNotEmpty(fileIdx)){
		    mav.setView(new AttachDownloadView());

		    FileInfo fileInfo = fileService.getFileInfo(fileIdx);
		    Path fileFullPath = null;
		    try {
		    	fileFullPath = Paths.get(fileInfo.getFilePath()).resolve(fileInfo.getFileNm()).normalize();
			} catch (Exception e) {
				e.getStackTrace();
			}
		    File downloadFile = new File(fileFullPath.toString());
		    mav.addObject("file", downloadFile);
		    mav.addObject("filename", fileInfo.getOrgFileNm());

		}
	    return mav;
	}

//	private ResponseEntity<Resource> getDownloadForm(String fileIdx) {
//		Resource resource = null;
//
//		FileInfo fileInfo = fileService.getFileInfo(fileIdx);
//
//		if(fileInfo == null)
//			return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
//		try {
//			resource = fileService.loadFileAsResource(fileInfo.getFilePath(), fileInfo.getLogicalFileName());
//
//			//return new ResponseEntity<Resource>(imgBytes, header, HttpStatus.OK);
//			return ResponseEntity.ok()
//					.contentLength(resource.contentLength())
//	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileInfo.getOrignalName().getBytes("UTF-8"), "ISO-8859-1") + "\"")
//	                .body(resource);
//		} catch(Exception e) {
//			log.error(" :: 이미지 조회 실패 ", e.getMessage());
//			e.printStackTrace();
//			return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping(value={Url.COMMON.FILE_DOWNLOAD})
	public void downloadFormFile(
            String fileIdx,
            HttpServletResponse response
    ){
        // id를 이용하여 파일의 정보를 읽어온다.
        // 이 부분은 원래 db에서 읽어오는 것인데 db부분은 생략했다.
		FileInfo fileInfo = fileService.getFileInfo(fileIdx);
        String originalFilename = fileInfo.getOrgFileNm();
        String contentType = "application/octet-stream";
        long fileSize = fileInfo.getFileSize();
        // 해당 파일이 이미 존재해야한다.
        Path saveFileName = Paths.get(fileInfo.getFilePath()).resolve(fileInfo.getFileNm()).normalize();

        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", ""+ fileSize);
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");

        java.io.File readFile = new java.io.File(saveFileName.toString());
        if(!readFile.exists()){ // 파일이 존재하지 않다면
            throw new RuntimeException("file not found");
        }

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(readFile);
            FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
            response.getOutputStream().flush();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally {
            try {
                fis.close();
            }catch(Exception ex){
                // 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
            }
        }
    }
}
