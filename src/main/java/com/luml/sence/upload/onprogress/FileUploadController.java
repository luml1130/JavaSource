package com.luml.sence.upload.onprogress;

/**
 * @author luml
 * @description
 * @date 2026/5/24
 */
//@RestController
public class FileUploadController {
    // 上传目录
    private static final String UPLOAD_DIR = "uploads/";

   // @PostMapping("/api/upload")
    /*public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件为空");
        }

        try {
            // 创建上传目录
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 生成唯一文件名，防止覆盖
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String fileName = UUID.randomUUID().toString() + extension;

            // 保存文件
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("文件上传成功: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("上传失败: " + e.getMessage());
        }
    }*/
}
