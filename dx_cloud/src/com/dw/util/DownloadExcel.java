package com.dw.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.hibernate.service.spi.ServiceException;

public class DownloadExcel {
	public void downloadExcel(HttpServletResponse response, List<Map<String,String>> list) {

        // 创建工作表
        WritableWorkbook book = null;
        response.reset();

        response.setCharacterEncoding("UTF-8");// 设置字符集

        // 创建工作流
        OutputStream os = null;
        try {

            // 设置弹出对话框
            response.setContentType("application/DOWLOAD");
            response.setCharacterEncoding("UTF-8");

            // 设置工作表的标题
            response.setHeader("Content-Disposition", "attachment; filename=Norecord_Social_Credit_Code.xls");// 设置生成的文件名字
            os = response.getOutputStream();

            // 初始化工作表
            book = Workbook.createWorkbook(os);

        } catch (IOException e1) {

             
            throw new ServiceException("导出失败", e1);
        }
        try {

            // 以下为excel表格内容
            // int nCount = list.size();
            WritableSheet sheet = book.createSheet("社信代无关联及名称不一致保存结果", 0);

            // 生成名工作表，参数0表示这是第一页
            // int nI = 1;

            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "序号"));
            sheet.addCell(new jxl.write.Label(1, 0, "名称(查询)"));
            sheet.addCell(new jxl.write.Label(2, 0, "经营状态"));
            sheet.addCell(new jxl.write.Label(3, 0, "名称(所填企业名)"));
            sheet.addCell(new jxl.write.Label(4, 0, "统一社会信用代码"));
            sheet.addCell(new jxl.write.Label(5, 0, "申请日期"));

            // 将数据追加
            for (int i = 1; i < list.size() + 1; i++) {

                sheet.addCell(new jxl.write.Label(0, i, String.valueOf(i)));// 序号从1开始
                sheet.addCell(new jxl.write.Label(1, i, ""));
             ;

                // 设置日期格式
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                String appDateStr = sf.format("2017-08-01");
                sheet.addCell(new jxl.write.Label(5, i, appDateStr));// 申请日期
            }
            book.write();
            book.close();
        } catch (Exception e) {
            throw new ServiceException("导出失败", e);
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
