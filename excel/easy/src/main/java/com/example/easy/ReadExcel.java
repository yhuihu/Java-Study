package com.example.easy;

import com.alibaba.excel.EasyExcel;
import com.example.easy.model.BookExcel;
import com.example.easy.model.TableExcel;
import com.example.easy.util.BookListener;
import com.example.easy.util.ExcelListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhhu
 * @date 2021/6/7
 * @description
 */
public class ReadExcel {
    public static void main(String[] args) throws FileNotFoundException {
        File bookFile = new File("C:\\Users\\yhhu\\Desktop\\0607-负债组所需字段命名.xlsx");
        BookListener bookListener = new BookListener();
        EasyExcel.read(new FileInputStream(bookFile), BookExcel.class, bookListener).sheet(0).doRead();
        ConcurrentHashMap<String, String> bookListenerDatas = bookListener.getDatas();
        File file = new File("C:\\Users\\yhhu\\Desktop\\0607-整合-bcls_table-负债.xls");
        ExcelListener excelListener = new ExcelListener();
        EasyExcel.read(new FileInputStream(file), TableExcel.class, excelListener).sheet(2).doRead();
        List<TableExcel> datas = excelListener.getDatas();
        for (TableExcel data : datas) {
            if (data.getColEnName() != null && !data.getColEnName().equals("")) {
                String result = bookListenerDatas.get(data.getColEnName());
                if (result != null) {
                    data.setColName(result);
                }
                System.out.println(data.getColName());
            } else {
                System.out.println(" ");
            }
        }
        ConcurrentHashMap<String, String> lengthMap = ExcelListener.lengthMap;
        List<String> nameList = BookListener.nameList;
        for (String name : nameList) {
            String name1 = lengthMap.get(name);
            if (name1 == null || name1.equals("")) {
                System.out.println("undefined");
            } else {
                System.out.println(name1);
            }
        }
    }
}
