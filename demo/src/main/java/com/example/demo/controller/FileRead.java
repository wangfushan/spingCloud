package com.example.demo.controller;

import com.example.demo.common.vo.CsvImportBo;
import com.example.demo.common.vo.ResultCode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;


@RestController
@RequestMapping("/FileRead")
public class FileRead {

    /**
     * 全部读取所有内容
     */
/*    public static void main(String[] args) throws Exception{

        // 第1步、使用File类找到一个文件
        File file=new File("E:" + File.separator + "123.txt");

        //第二步   通过子类实例父类对象

        Reader reader=new FileReader(file);//准备好一个输入对象

        //第三步 进行读写

        char c[]=new char[1024];

        int temp=0; //接收每一个内容
        int len=0;//读取内容

        while((temp=reader.read())!=-1){
            // 如果不是-1就表示还有内容，可以继续读取
            c[len] = (char)temp ;
            len++ ;
        }
        // 第4步、关闭输出流
        reader.close() ;                        // 关闭输出流

        System.out.println("内容为："+"/n" + new String(c,0,len)) ;    // 把字符数组变为字符串输出

    }*/


    /**
     * 一行一行的读取内容
     * @param args
     * @throws IOException
     */
/*    public static void main(String[] args) throws IOException {
        File file = new File("E:" + File.separator + "123.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
    }*/

    /**
     * 写入内容
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File file = new File("E:" + File.separator + "123.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append("hello");
        bufferedWriter.close();
        fileWriter.close();
    }


    @PostMapping("/file")
    public String fileRead(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "空";
        }

        InputStream ins = file.getInputStream();
        File toFile = new File(file.getOriginalFilename());
        inputStreamToFile(ins, toFile);
        ins.close();

        FileWriter fileWriter = new FileWriter(toFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.append("hello");
        String a = bufferedWriter.toString();
        bufferedWriter.close();
        fileWriter.close();

        FileReader fileReader = new FileReader(toFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        StringBuffer data = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
            data.append(str);
        }

        return a;
    }

    @PostMapping("/csv")
    public String csvFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.getOriginalFilename().endsWith("csv") == false) {
            return "文件格式不对！";
        }
        String a = file.getOriginalFilename();
        String b = file.getName();

        if (file.getOriginalFilename().equals("第三方信息_1")){
            return "文件不匹配！";
        }
        // 第一步  把文件转为InputStream流
        InputStream inputStream = file.getInputStream();
        // 第二步  把文件流转为reader 创建 CSV转换对象
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim();
        List list = new ArrayList();
        // 第三步 循环每一行处理数据
        for (CSVRecord csvRecord : csvFormat.parse(reader)) {
            Map<String, Object> map = new HashMap<>();
            map.put("商户编号", csvRecord.get("商户编号"));
            list.add(map);
        }
        return list.toString();
    }


    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
