package kr.co.rci.esign.admin.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.rci.esign.admin.dao.FileMapper;
import kr.co.rci.esign.admin.domain.CoTopComponent;
import kr.co.rci.esign.admin.domain.FileInfo;

@Service
@PropertySource("classpath:config.properties")
public class FileService extends CoTopComponent{
	@Autowired
	FileMapper fileMapper;

	/** 파일 업로드 기본 경로 */
	private static final String UPLOAD_FILE_PATH = "file.root.path";

	@Value("${file.root.path}")
	private String fileRootPath;

	public FileInfo uploadFile(HttpServletRequest req) throws IOException {
		FileInfo result = new FileInfo();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

		String uploadDirPath = fileRootPath; //appEnv.getProperty(UPLOAD_FILE_PATH);

		// Upload 경로에 디렉토리가 없을 경우 만듬
		Path uploadDir = Paths.get(uploadDirPath);
		// 업로드 디렉도리가 존재하지 않거나 디렉도리가 아닐경우 생성합니다.
		if (!Files.isDirectory(uploadDir)) {
			Files.createDirectories(uploadDir);
		}

		for (MultipartFile mFile : multipartRequest.getFileMap().values()) {

			UUID tempFileName = UUID.randomUUID();
			String originalFileName = mFile.getOriginalFilename(); // 원본 파일명
			String fileExt = FilenameUtils.getExtension(originalFileName);

			if (originalFileName.toLowerCase().endsWith(".tar.bz2")) {
				fileExt = "tar.bz2";
			} else if (originalFileName.toLowerCase().endsWith(".tar.gz")) {
				fileExt = "tar.gz";
			}
			String logicalFileName = tempFileName + "." + fileExt;
			//File file = new File(uploadDir + "/" + logicalFileName);
			//transferTo(mFile, file);

			byte[] fileBytes = mFile.getBytes();
			Path filePath = Paths.get(uploadDir + "/" + logicalFileName);
			Files.write(filePath, fileBytes);

			// 사이즈가 0일 경우 파일이 존재하지 않는 것으로 판단.
			/*if( mFile.getSize() == 0 ) {
				log.warn("Abort upload file. empty fle");
				throw new RuntimeException("Abort upload file. empty fle");
			}*/

			result.setFilePath(uploadDirPath);
			result.setOrgFileNm(originalFileName);
			result.setFileNm(logicalFileName);
			result.setFileExt(fileExt);
			result.setContentType(mFile.getContentType());
			result.setFileSize(Files.size(filePath));

			result.setCreateId(loginUserName());

			fileMapper.insertFile(result);
			if (isEmpty(result.getFileNo())) {
				throw new RuntimeException("Unable to Save File Info : An error occurred while file upload.");
			}
			break;
		}

		return result;
	}

	public FileInfo getFileInfo(String idx) {
		return fileMapper.getFileInfo(idx);
	}

	private boolean transferTo(MultipartFile multipart, File file) {
		if (multipart != null && file != null) {
			try {
				multipart.transferTo(file);
				return true;
			} catch (Exception e) {
				try {
					FileUtils.copyInputStreamToFile(multipart.getInputStream(), file);
					return true;
				} catch (IOException e1) {
					//					logger.error(e.getMessage(), e);
				}
			}
		}
		return false;
	}

	public FileInfo getImage(String filePath) {
		FileInfo result = new FileInfo();

		String ext = "";

		File f = FileUtils.getFile(filePath);
		ext = FilenameUtils.getExtension(filePath);

		result.setFileObject(f);
		result.setFileExt(ext);

		return result;
	}

	public Resource loadFileAsResource(String filePath, String fileName) {
		Path fileFullPath = Paths.get(filePath).resolve(fileName).normalize();
		Resource resource;
		try {
			resource = new UrlResource(fileFullPath.toUri());
			//resource = new ServletContextResource(servletContext, fileFullPath.toAbsolutePath().toString());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MalformedURLException("File not found " + fileName);
			}
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
		}

		return null;

	}

	public void fileDownload(String fileSeq, HttpServletResponse response) throws IOException {

		FileInfo fileInfo= getFileInfo(fileSeq);
        String storedFileName = fileInfo.getFileNm();
        String originalFileName = fileInfo.getOrgFileNm();
        String uploadDirPath = appEnv.getProperty(UPLOAD_FILE_PATH);
        byte fileByte[] = FileUtils.readFileToByteArray(new File(uploadDirPath+storedFileName));

        response.setContentType("application/octet-stream");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);

        response.getOutputStream().flush();
        response.getOutputStream().close();

	}

	public void fileDelete(String fileSeq,String modNo) {
		Map<String,Object> map = new HashMap<>();

		map.put("fileSeq", fileSeq);
		map.put("modNo", modNo);
		fileMapper.fileDelete(map);

	}
}
