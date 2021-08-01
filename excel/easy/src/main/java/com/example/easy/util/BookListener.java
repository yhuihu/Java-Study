package com.example.easy.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.easy.model.BookExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhhu
 * @date 2021/6/7
 * @description
 */
public class BookListener extends AnalysisEventListener<BookExcel> {

    public static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

    public static List<String> nameList = new ArrayList<>();

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(BookExcel object, AnalysisContext context) {
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        try {
            concurrentHashMap.put(object.getColName(), object.getColEnName());
            nameList.add(object.getColName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //根据业务自行 do something
        doSomething();
    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public ConcurrentHashMap<String, String> getDatas() {
        return concurrentHashMap;
    }

}
