package kr.co.rci.esign.admin.dao;

import java.util.Map;

import kr.co.rci.esign.admin.domain.FileInfo;

public interface FileMapper {
	/**
	 * Insert file.
	 *
	 * @param file the file
	 * @return the int
	 */
	int insertFile(FileInfo file);

	/**
	 * Gets the file info.
	 *
	 * @param idx the idx
	 * @return the file info
	 */
	FileInfo getFileInfo(String idx);
	/**
	 * Delete file
	 *
	 * @param fileSeq,modNo
	 */
	void fileDelete(Map	 map);
}
