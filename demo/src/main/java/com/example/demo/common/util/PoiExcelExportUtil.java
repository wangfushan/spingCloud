package com.example.demo.common.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/****
 * @author fan.fc
 */
public class PoiExcelExportUtil {
    public static SXSSFWorkbook getSXSSFWorkbook(String sheetName, String[] title, String[][] values, SXSSFWorkbook wb) {
        // 第一步，创建一个SXSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new SXSSFWorkbook(500);
        }
        wb.setCompressTempFiles(false);
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");//设置字体名称
        font.setFontHeightInPoints((short) 13);//设置字号
        cellStyle.setFont(font);
        cellStyle.setWrapText(true);
        SXSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        SXSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        //声明列对象
        SXSSFCell cell = null;
        //创建标题
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, 20 * 500);//设置单元格宽度
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            //渲染单元格
            cell.setCellStyle(cellStyle);
        }
        sheet.trackAllColumnsForAutoSizing();

        //创建内容
        for (int i = 0; i < values.length; i++) {

            row = sheet.createRow(i + 1);
            row.setRowStyle(cellStyle);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                SXSSFCell rowCell = row.createCell(j);
                rowCell.setCellStyle(cellStyle);
                rowCell.setCellValue(values[i][j]);
                //   sheet.autoSizeColumn(j); 使用导出特别慢

            }
            //  row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        }

        return wb;
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
