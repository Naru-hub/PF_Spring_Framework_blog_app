package com.example.blogapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

	private static final String UPLOADED_FOLDER = "src/main/resources/static/uploads/";

	/**
	 * 画像ファイルの処理と保存
	 * @param file
	 * @return uniqueFilename ファイル名
	 * @throws IOException
	 */
	public String handleImageUpload(MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			File dir = new File(UPLOADED_FOLDER);
			/** ディレクトリが存在しない場合作成 */
			if (!dir.exists()) {
				dir.mkdirs();
			}
			/** タイムスタンプを用いた一意なファイル名を生成 */
			String originalFilename = file.getOriginalFilename();
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String uniqueFilename = timestamp + "_" + originalFilename;

			/** 指定されたパスにファイルを保存 */
			Path path = Paths.get(dir.getAbsolutePath() + File.separator + uniqueFilename);
			Files.write(path, file.getBytes());
			
			// ファイル名を返す
			return uniqueFilename;
		}
		return null;
	}
	
	/**
	 * 画像ファイルを削除
	 * @param filename
	 */
	public void deleteImage(String filename) {
        if (filename != null && !filename.isEmpty()) {
            File file = new File(UPLOADED_FOLDER + filename);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
