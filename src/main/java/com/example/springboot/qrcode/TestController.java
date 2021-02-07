package com.example.springboot.qrcode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2021/2/5 15:52
 * @Author lichengshuai
 */
@RestController
@RequestMapping("/qrcode")
public class TestController {
    /**
     * 生成二维码并保存到指定的目录下
     */
    @GetMapping("/create")
    public void createQrCodeForFile(){
        String codeContent = "https://www.baidu.com/";
        File file = new File("C:/secFile");
        QRBarCodeUtil.createCodeToFile(codeContent,file,"baidu");
    }

    /**
     * 页面初始化
     * @return
     */
    @GetMapping("/view")
    public ModelAndView page(){
        return new ModelAndView("qrCode");
    }

    /**
     * 根据内容生成二维码并输出到页面显示
     * @param codeContent 二维码内容
     * @param response
     */
    @GetMapping("/qrCode")
    public void getQRCode(String codeContent, HttpServletResponse response) {
        System.out.println("codeContent=" + codeContent);
        try {
            /**
             * 调用工具类生成二维码并输出到输出流中
             */
            QRBarCodeUtil.createCodeToOutputStream(codeContent, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  解析二维码图片内容
     * @return
     */
    @GetMapping("/getInfoByQrcode")
    public String getInfoByQrcode(){
        File qrCodeFile = new File("C:\\secFile\\baidu.png");
        String qrCodeStr = QRBarCodeUtil.parseQRCodeByFile(qrCodeFile);
        System.out.println(qrCodeStr);
        return qrCodeStr;
    }
}
