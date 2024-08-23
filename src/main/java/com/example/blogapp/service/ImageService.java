package com.example.blogapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private static final String UPLOADED_FOLDER = "src/main/resources/static/uploads/";

    /**
     * 画像ファイルの処理と保存
     */
    public String handleImageUpload(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            File dir = new File(UPLOADED_FOLDER);
            /** ディレクトリが存在しない場合作成 */
            if (!dir.exists()) {
                dir.mkdirs();
            }

            /** 指定されたパスにファイルを保存 */
            Path path = Paths.get(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            // ファイルパスを相対パスとして返す
            return "/uploads/" + file.getOriginalFilename(); 
        }
        return null;
    }
}
