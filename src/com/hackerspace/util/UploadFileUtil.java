package com.hackerspace.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class UploadFileUtil{
	
	/**
	 * 说明：上传单个文件
	 * @throws Exception 
	 */
	public static boolean uploadFile(File f,String fileFileName,String fileUrl) {
		try {
			File saveFile=new File(new File(fileUrl),fileFileName);//文件路径和文件名
			if(!saveFile.getParentFile().exists()) {
				saveFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(f, saveFile);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
