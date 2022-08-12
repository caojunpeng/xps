package com.cao.xps.service.excel;

import com.alibaba.fastjson.JSONObject;
import com.cao.xps.common.utils.XpsFileUtils;
import com.cao.xps.common.utils.XpsStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class ExcelUtile {

    /**
     * 解析excel
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Map<String,Object>> readExcelToMaps(File file) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        List<Map<String,Object>> acclist = new ArrayList<Map<String,Object>>();
        boolean isE2007 = false; // 判断是否是excel2007格式
        if (file.getName().endsWith("xlsx"))
            isE2007 = true;
        try {
            InputStream input = new FileInputStream(file); // 建立输入流
            Workbook wb = null;

            // 根据文件格式(2003或者2007)来初始化
            if (isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i); // 获得第一个表单
                if (sheet.getSheetName().equals("Sheet1")) {
                    Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
                    while (rows.hasNext()) {
                        Row row = rows.next(); // 获得行数据
                        if (row.getRowNum() == 0) {
                            continue;
                        }
                        Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
                        Map<String,Object> maps = new HashMap<>();
                        while (cells.hasNext()) {
                            Cell cell = cells.next();
                            if (cell.getColumnIndex() == 0 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("1",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 1 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("2",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 2 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("3",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 3 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("4",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 4 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("5",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 5 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("6",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 6 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("7",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 7 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("8",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 8 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("9",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 9 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("10",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 10 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("11",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 11 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("12",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 12 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("13",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 13 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("14",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 14 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("15",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 15 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("16",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 16 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("17",cell.getStringCellValue().trim());
                            }
                            if (cell.getColumnIndex() == 17 ) {
                                cell.setCellType(CellType.STRING);
                                maps.put("18",cell.getStringCellValue().trim());
                            }
                        }
                        acclist.add(maps);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return acclist;
    }

    /**
     * excel的本地保存
     * @param path
     * @param fileName
     * @param wb
     */
    public static void saveFile(String path, String fileName, XSSFWorkbook wb) {
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.mkdir();
            }
            File files = new File(path+file.separator+fileName);
            OutputStream os = new FileOutputStream(files);
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel的浏览器下载
     * @param response
     * @param fileName
     * @param wb
     * @param request
     */
    public static void downFile(HttpServletResponse response, String fileName, XSSFWorkbook wb, HttpServletRequest request) {
        try {
            setResponseHeader(response, request, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览器下载编码配置
     * @param response
     * @param request
     * @param fileName
     */
    public static void setResponseHeader(HttpServletResponse response, HttpServletRequest request, String fileName) {
        try {
            response.reset();
            String userAgent = request.getHeader("User-Agent");
            byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
            fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName)); // 文件名外的双引号处理firefox的空格截断问题
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 生成excel（只有一个sheel）
     * @param sheetName
     * @param titlList
     * @param selectAllByParm
     * @param wb1
     * @return
     */
    public static XSSFWorkbook getXSSFExportExcel(String sheetName, List<String> titlList, List<List<String>> selectAllByParm, XSSFWorkbook wb1) {
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = wb1;
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        getSheet(sheetName, titlList, selectAllByParm, wb);
        return wb;
    }

    /**
     * 生成sheet（不合并单元格）
     * @param sheetName
     * @param titlList
     * @param selectAllByParm
     * @param wb
     */
    public static void getSheet(String sheetName, List<String> titlList, List<List<String>> selectAllByParm,XSSFWorkbook wb) {
        XSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < titlList.size(); i++) {
            sheet.setColumnWidth(i, 100 * 70);
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        XSSFCell cell = null;
        // 创建标题
        for (int i = 0; i < titlList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(titlList.get(i));
            cell.setCellStyle(style);
        }

        //导出不需要合并的单元格
        if (!selectAllByParm.isEmpty()&&selectAllByParm!=null) {
            // 创建内容
            for (int i = 0; i < selectAllByParm.size(); i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0; j < titlList.size(); j++) {
                    row.createCell(j).setCellValue(selectAllByParm.get(i).get(j));
                }
                row.setRowStyle(style);
            }
        }
    }
    /**
     * 生成sheet（合并单元格）
     * @param sheetName：sheet名称
     * @param titlList:标题头
     * @param selectAllByParm：数据
     * @param wb：新创建的excel
     */
    public static void getSheetCombine(String sheetName, List<String> titlList, List<List<String>> selectAllByParm,XSSFWorkbook wb) {
        XSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < titlList.size(); i++) {
            sheet.setColumnWidth(i, 70 * 70);
        }
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        XSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        XSSFCell cell = null;
        // 创建标题
        cell = row.createCell(0);
        cell.setCellValue("标题");
        cell.setCellStyle(style);
        //四个参数：起始行号，终止行号，起始列号，终止列号
        CellRangeAddress region = new CellRangeAddress(0,0, 0, 3);
        sheet.addMergedRegion(region);
        //导出不需要合并的单元格
        if (!selectAllByParm.isEmpty()&&selectAllByParm!=null) {
            // 创建内容
            for (int i = 0; i < selectAllByParm.size(); i++) {
                //跳过标题头
                row = sheet.createRow(i + 1);
                for (int j = 0; j < titlList.size(); j++) {
                    row.createCell(j).setCellValue(selectAllByParm.get(i).get(j));
                }
                row.setRowStyle(style);
            }
        }
    }

    /**
     *  生成excel(多个sheet)
     * @param listMaps
     * @return
     */
    public static XSSFWorkbook getXSSFExportExcels(List<Map<String, Object>> listMaps) {
        // 第一步，创建一个webbook，对应一个Excel文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        for (Map<String, Object> dataMap : listMaps) {
            String sheetName = (String) dataMap.get("sheetName");
            List<String> titlList=(List<String>) dataMap.get("titlList");
            List<List<String>> selectAllByParm=(List<List<String>>) dataMap.get("selectAllByParm");
            String mergeCell1=(String) dataMap.get("mergeCell1");
            getSheet(sheetName, titlList, selectAllByParm,wb);
        }
        return wb;
    }
    public static  String getString(String str){
        if(XpsStringUtils.isNotBlank(str) && !"null".equals(str)){
            if(str.indexOf("\"")>-1){
                str = str.replaceAll("\"","'");
            }
            return str;
        }
        return "";
    }

    public static void main(String[] args) {
        try {
            List<String> sqlList = new ArrayList<>();
            File file = new File("C:\\Users\\peng\\Desktop\\test_airports.xlsx");
            List<Map<String, Object>> maps = readExcelToMaps(file);
            for (int i =0; i<maps.size();i++) {
                String sql= "("+maps.get(i).get("1")+",\""
                        +getString(maps.get(i).get("2")+"")+"\",\""
                        +getString(maps.get(i).get("3")+"")+"\",\""
                        +getString(maps.get(i).get("4")+"")+"\",\""
                        +getString(maps.get(i).get("5")+"")+"\",\""
                        +getString(maps.get(i).get("6")+"")+"\",\""
                        +getString(maps.get(i).get("7")+"")+"\",\""
                        +getString(maps.get(i).get("8")+"")+"\",\""
                        +getString(maps.get(i).get("9")+"")+"\",\""
                        +getString(maps.get(i).get("10")+"")+"\",\""
                        +getString(maps.get(i).get("11")+"")+"\",\""
                        +getString(maps.get(i).get("12")+"")+"\",\""
                        +getString(maps.get(i).get("13")+"")+"\",\""
                        +getString(maps.get(i).get("14")+"")+"\",\""
                        +getString(maps.get(i).get("15")+"")+"\",\""
                        +getString(maps.get(i).get("16")+"")+"\",\""
                        +getString(maps.get(i).get("17")+"")+"\",\""
                        +getString(maps.get(i).get("18")+"")+"\"),";
                sqlList.add(sql);
            }
            XpsFileUtils.writeObject(sqlList,"C:\\Users\\peng\\Desktop\\sql.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
