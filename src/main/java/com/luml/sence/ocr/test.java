package com.luml.sence.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @author luml
 * @description
 * @date 2026/3/29
 * 安装 Tesseract OCR 引擎‌：首先需要在你的系统上安装 Tesseract OCR 引擎。你可以从 Tesseract GitHub 仓库 下载适用于你操作系统的安装包。
 * ‌获取语言数据包 (Trained Data)‌：Tesseract 需要语言数据包来识别特定语言的字符。这些数据包通常以 .traineddata 文件形式存在。
 *  你可以从 Tesseract 训练数据仓库 下载你需要的语言包，例如 eng.traineddata (英文) 或 chi_sim.traineddata (简体中文)。
 *  下载后，将这些文件放在 Tesseract 安装目录下的 tessdata 文件夹中，或者在代码中指定一个包含这些文件的路径。
 */
public class test {
    public static void main(String[] args) {
        ITesseract tesseract = new Tesseract();
        // 设置Tessdata路径（根据实际安装位置调整）
        tesseract.setDatapath("tessdata");
        // 设置语言，如 eng 表示英文，chi_sim表示简体中文
        tesseract.setLanguage("eng");

        try {
            String result = tesseract.doOCR(new File("test.png"));
            System.out.println("识别结果:");
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
