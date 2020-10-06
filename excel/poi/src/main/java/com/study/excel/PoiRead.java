package com.study.excel;

import com.study.excel.entity.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiger
 * @date 2020-06-18
 * @see com.study.excel
 **/
public class PoiRead {
    private static Workbook workbook = null;
    private static FileInputStream inputStream = null;

    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("target.xlsx");
        File file = classPathResource.getFile();
        // 获取Excel工作簿
        inputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(inputStream);
        List<Student> list = parseExcel(workbook);
        System.out.println(list.size());
    }

    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<Student> parseExcel(Workbook workbook) {
        List<Student> resultDataList = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum() + 1;
            // 解析每一行的数据，构造数据对象
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = firstRowNum; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                Student resultData = convertRowToData(row);
                resultDataList.add(resultData);
            }
        }
        return resultDataList;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static Student convertRowToData(Row row) {
        Student resultData = new Student();
        Cell cell;
        int cellNum = 0;
        // 获取id
        cell = row.getCell(cellNum++);
        int id = (int) cell.getNumericCellValue();
        resultData.setId(id);
        // 获取姓名
        cell = row.getCell(cellNum);
        resultData.setName(cell.getStringCellValue());
        return resultData;
    }
}
