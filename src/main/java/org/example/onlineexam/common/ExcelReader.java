package org.example.onlineexam.common;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.onlineexam.teacher.vo.QuestionVO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Log4j2
public class ExcelReader {

    public static List<QuestionVO> readInputStream(InputStream in)throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(in);

//Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

//Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();


        log.info(rowIterator);

        //1번행은 제목이므로 skip
        rowIterator.next();

        List<QuestionVO> quizVOList = new ArrayList<>();


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if(row.getCell(0) == null){
                break;
            }

            int idx = 0;

            QuestionVO vo = QuestionVO.builder()
                    .q_num((int)(int)row.getCell(idx++).getNumericCellValue())
                    .q_view(row.getCell(idx++).getStringCellValue())
                    .q_answer1(row.getCell(idx++).getStringCellValue())
                    .q_answer2(row.getCell(idx++).getStringCellValue())
                    .q_answer3(row.getCell(idx++).getStringCellValue())
                    .q_answer4(row.getCell(idx++).getStringCellValue())
                    .q_answer5(row.getCell(idx++).getStringCellValue())
                    .q_right((int)(row.getCell(idx++).getNumericCellValue()))
                    .build();

            log.info("---------------------");
            log.info(vo);

            quizVOList.add(vo);
        }


        return quizVOList;

    }


}
