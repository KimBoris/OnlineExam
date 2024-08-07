package org.example.onlineexam.exam;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.onlineexam.common.ExcelReader;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(value="/exam/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024*10,
maxFileSize = 1024*1024*50,
maxRequestSize = 1024*1024*100)
@Log4j2
public class ExamUploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("examFile");

        @Cleanup InputStream in = filePart.getInputStream();

        try
        {
            List<QuestionVO> quizVOList = ExcelReader.readInputStream(in);
            log.info(quizVOList);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
