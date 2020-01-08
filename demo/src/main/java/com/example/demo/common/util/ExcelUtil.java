package com.example.demo.common.util;



import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelUtil {

    public static void  getExcelDownload(List<Map<String,Object>> maps, HttpServletResponse response) throws IOException {
        getExcel(maps, response);
    }

    public static void getExcel(List<Map<String,Object>> list, HttpServletResponse response) throws IOException {
        // 获取文件名 规则：用户信息+当前时间毫秒数
        String fileName = "合同上传" + System.currentTimeMillis() + ".xls";
        // 获取用户下载路径 当前用户主目录
//        String propertyPath = System.getProperty("user.home") + File.separator + fileName;
//       log.info(propertyPath);
        try {
            // 打开输出流
//            OutputStream os = new FileOutputStream(propertyPath);
            // 工作区
            HSSFWorkbook wb = new HSSFWorkbook();
            // sheet
            HSSFSheet sheet = wb.createSheet("合同上传信息");
            // 创建单元格并设置表头
            HSSFCellStyle style = wb.createCellStyle();
            HSSFRow row0 = sheet.createRow(0);
            HSSFCell title = row0.createCell(0);
            /*title.setCellValue("排序");*/
            title.setCellStyle(style);
            // sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
            // 第一行
            HSSFRow row1 = sheet.createRow(1);
            // 设置第一行信息 表头
            row1.createCell(0).setCellValue("排序");
            row1.createCell(1).setCellValue("订单号");
            row1.createCell(2).setCellValue("下单人");
            row1.createCell(3).setCellValue("开票城市");
            row1.createCell(4).setCellValue("城市ID");
            row1.createCell(5).setCellValue("订单类型");
            row1.createCell(6).setCellValue("订单状态");
            row1.createCell(7).setCellValue("交车时间");
            row1.createCell(8).setCellValue("文件类型");
            row1.createCell(9).setCellValue("文件状态");
            row1.createCell(10).setCellValue("上传数量");
            row1.createCell(11).setCellValue("操作人");
            row1.createCell(12).setCellValue("更新时间");
            row1.createCell(13).setCellValue("创建时间");
            // 遍历信息集合
           for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i + 2);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(list.get(i).get("order_no").toString());
                row.createCell(2).setCellValue(list.get(i).get("order_person")==null ? " ":list.get(i).get("order_person").toString());
                row.createCell(3).setCellValue(list.get(i).get("city_name") ==null ? " " :list.get(i).get("city_name").toString());
                row.createCell(4).setCellValue(list.get(i).get("city_id") ==null ? " ":list.get(i).get("city_id").toString());
                row.createCell(5).setCellValue(list.get(i).get("order_type") ==null ?" ":list.get(i).get("order_type").toString());
                row.createCell(6).setCellValue("车辆已交付");
                row.createCell(7).setCellValue(list.get(i).get("order_status_date") ==null ?" ":list.get(i).get("order_status_date").toString());
                row.createCell(8).setCellValue(list.get(i).get("type_name") ==null ?" ":list.get(i).get("type_name").toString());
                if(list.get(i).get("count")==null ||"0".equals(list.get(i).get("count").toString())){
                    row.createCell(9).setCellValue("未上传");
                }else {
                    row.createCell(9).setCellValue("已上传");
                }
                row.createCell(10).setCellValue(list.get(i).get("count") ==null ?" ":list.get(i).get("count").toString());
                row.createCell(11).setCellValue(list.get(i).get("uploadUser")==null ?" ":list.get(i).get("uploadUser").toString());
                row.createCell(12).setCellValue(list.get(i).get("update_time") ==null ?" ":list.get(i).get("update_time").toString());
                row.createCell(13).setCellValue(list.get(i).get("create_time") ==null?" ":list.get(i).get("create_time").toString());


                row = null;
            }
           response.addHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
           response.setContentType("application/vnd.ms-excel;charset=utf-8");
            wb.write(response.getOutputStream());
            wb.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        download(propertyPath, response);
    }

    public static void download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            // 取得文件名
            String fileName = file.getName();
            // 打开输入流
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            response.addHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getExcelByXSS(List<Map> list, HttpServletResponse response) {
        // 获取文件名 规则：用户信息+当前时间毫秒数
        String fileName = "用户信息" + System.currentTimeMillis() + ".xlsx";
        // 获取用户下载路径 当前用户主目录
        String propertyPath = System.getProperty("user.home") + File.separator + fileName;
        System.out.println(propertyPath);
        try {
            // 打开输出流
            OutputStream os = new FileOutputStream(propertyPath);
            // 工作区
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("合同上传信息列表");
            XSSFRow row0 = sheet.createRow(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
