package com.luml.sence.upload.onprogress;

/*import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;*/
import java.io.File;
/**
 * @author luml
 * @description
 * 将文件上传到阿里云 OSS，SDK 内置了 ProgressListener 接口，可以非常方便地获取上传进度。
 * @date 2026/5/24
 */
public class OssUploadWithProgress {
    public static void main(String[] args) {
        // 配置信息
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "yourAccessKeyId";
        String accessKeySecret = "yourAccessKeySecret";
        String bucketName = "yourBucketName";
        String objectName = "exampledir/exampleobject.txt";
        String localFilePath = "D:\\localpath\\examplefile.txt";

        // 创建 OSSClient 实例
        //OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建上传请求
            // PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFilePath));

            // 设置进度监听器
          /* putObjectRequest.setProgressListener(new ProgressListener() {
                private long bytesWritten = 0;
                private long totalBytes = -1;
                private boolean succeed = false;

                @Override
                public void progressChanged(ProgressEvent progressEvent) {
                    long bytes = progressEvent.getBytes();
                    ProgressEventType eventType = progressEvent.getEventType();

                    switch (eventType) {
                        case TRANSFER_STARTED_EVENT:
                            System.out.println("开始上传...");
                            break;
                        case REQUEST_CONTENT_LENGTH_EVENT:
                            this.totalBytes = bytes;
                            System.out.println("总文件大小: " + this.totalBytes + " bytes");
                            break;
                        case REQUEST_BYTE_TRANSFER_EVENT:
                            this.bytesWritten += bytes;
                            if (this.totalBytes != -1) {
                                int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                                System.out.println("上传进度: " + percent + "% (" + this.bytesWritten + "/" + this.totalBytes + ")");
                                // 在这里可以将进度发送给前端（例如通过 WebSocket 或存入 Session/Redis）
                            } else {
                                System.out.println("已上传: " + this.bytesWritten + " bytes");
                            }
                            break;
                        case TRANSFER_COMPLETED_EVENT:
                            this.succeed = true;
                            System.out.println("上传成功!");
                            break;
                        case TRANSFER_FAILED_EVENT:
                            System.out.println("上传失败!");
                            break;
                        default:
                            break;
                    }
                }
            });*/

            // 执行上传
            //ossClient.putObject(putObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ossClient.shutdown();
        }
    }
}
