package com.example.easy.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.easy.model.TableExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhhu
 * @date 2021/6/7
 * @description
 */
public class ExcelListener extends AnalysisEventListener<TableExcel> {

    /**
     * 自定义用于暂时存储data。
     * 可以通过实例获取该值
     */
    public static List<TableExcel> datas = new ArrayList<>();

    public static ConcurrentHashMap<String, String> lengthMap = new ConcurrentHashMap<>();


    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    @Override
    public void invoke(TableExcel object, AnalysisContext context) {
        Integer rowIndex = context.readRowHolder().getRowIndex();
        // 前四行为表头
        if (rowIndex > 2) {
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(object);
            //根据业务自行 do something
            if (object.getColEnName() != null && object.getTypeAndLength() != null) {
                if (object.getColEnName() != null && object.getTypeAndLength() != null && lengthMap.get(object.getColEnName()) == null) {
                    lengthMap.put(object.getColEnName(), object.getTypeAndLength());
                } else {
                    if (object.getTypeAndLength().equalsIgnoreCase(lengthMap.get(object.getTypeAndLength())))
                        System.out.println(object.getColEnName() + "长度出现差异，" + "原长度：" + lengthMap.get(object.getTypeAndLength()) + "----目标长度：" + object.getTypeAndLength());
                }
            }
            doSomething();
        }
    }

    /**
     * 根据业务自行实现该方法
     */
    private void doSomething() {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<TableExcel> getDatas() {
        return datas;
    }

    public void setDatas(List<TableExcel> datas) {
        this.datas = datas;
    }
}
