package com.example.demo.controller;

import com.example.demo.common.util.PoiExcelExportUtil;
import com.example.demo.entity.UserExportColumnVo;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController()
public class CsvDownLoanController {



    @RequestMapping("/listUserInfoExportExcel")
    public void listUserInfoExportExcel(HttpServletRequest request, HttpServletResponse response,String a) {
        List<UserExportColumnVo> listDate = new ArrayList<>();
        UserExportColumnVo aa=new UserExportColumnVo();
        aa.setStatus("ssssss");
        listDate.add(aa);
        String[] title = {"商户号", "商户名称", "用户类型", "结算类型", "用户状态", "层级关系", "销售人员", "用户创建时间", "首次激活时间"};
        //excel文件名
        String fileName = "总平台用户信息报表" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
        //sheet名
        String sheetName = "总平台用户信息";
        String[][] content = new String[listDate.size()][];
        for (int i = 0; i < listDate.size(); i++) {
            content[i] = new String[title.length];
            UserExportColumnVo userExportColumnVo = listDate.get(i);
            content[i][0] = userExportColumnVo.getStatus();
            content[i][1] = a;
            content[i][2] = "自己写";
            content[i][3] = a;
            content[i][4] = "自己写";
            StringBuilder levelRelation = new StringBuilder();
            content[i][5] = levelRelation.toString();
            content[i][6] = "第7个";
            content[i][7] = "第8个";
            content[i][8] = "第9个";
        }
        //SXSSFWorkbook
        PoiExcelExportUtil excel = new PoiExcelExportUtil();
        SXSSFWorkbook wb = excel.getSXSSFWorkbook(sheetName, title, content, null);
        //响应到客户端
        OutputStream outputStream = null;
        try {
            excel.setResponseHeader(response, fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != wb) {
                try {
                    wb.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
