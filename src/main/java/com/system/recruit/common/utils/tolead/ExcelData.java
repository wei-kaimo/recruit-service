package com.system.recruit.common.utils.tolead;


import com.alibaba.fastjson.JSONObject;
import com.system.recruit.common.utils.RSA.RSAUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/23 17:06
 */
public class ExcelData {
    private XSSFSheet sheet;

    /**
     * 构造函数，初始化excel数据
     * @param filePath  excel路径
     * @param sheetName sheet表名
     */
    public ExcelData(String filePath, String sheetName){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            sheet = sheets.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据行和列的索引获取单元格的数据
     * @param row
     * @param column
     * @return
     */
    public String getExcelDateByIndex(int row,int column){
        XSSFRow row1 = sheet.getRow(row);
        XSSFCell cell = row1.getCell(column);
        DataFormatter dataFormatter = new DataFormatter();
        String value = dataFormatter.formatCellValue(cell);
        return value;
    }

    /**
     * 根据某一列值为“******”的这一行，来获取该行第x列的值
     * @param caseName
     * @param currentColumn 当前单元格列的索引
     * @param targetColumn 目标单元格列的索引
     * @return
     */
    public String getCellByCaseName(String caseName,int currentColumn,int targetColumn){
        String operateSteps="";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            XSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if(cell.equals(caseName)){
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }


    public List<Map<String,Object>> getCellToList(){
        List<Map<String,Object>> rest = new ArrayList<>();
        Map<Integer,String> keyMap = new HashMap<>();
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            Map<String,Object> map = new HashMap<>();
            if (i==0){
                continue;
            }
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for(int j=0;j<columns;j++){
                XSSFCell cell = row.getCell(j);
                DataFormatter dataFormatter = new DataFormatter();
                String value = dataFormatter.formatCellValue(cell);
                if (i==1) {
                    keyMap.put(j, value);
                    continue;
                }
                if (value != null && ! "".equals(value)){
                    map.put(keyMap.get(j),value);
                }
            }
            rest.add(map);
        }
        rest.remove(0);
        return rest;
    }

    public List<ToLeadPojo> getCellToPojoList(){
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHQZrqgUQ23BazDSI5iMb+OdQmW8EvTvbpB1BPzZltNT+8kt4MzcrlLigC2sJRpk7CPV9lz+glZtZ3FOeLXV2CKDtknsVSuyrp0Qv/0HNhyWQ2+rRACK4ANBtu5zxtCxR17rS0xtiI+bQ9zGPVwicU6LycDC1x8kR4jXEUXi0XywIDAQAB";
        List<ToLeadPojo> restList = new ArrayList<>();
        List<Map<String,Object>> list = this.getCellToList();
        for (Map<String, Object> map : list) {
            String newPositionRequirements = "";
            String positionRequirements = (String) map.get("positionRequirements");
            String[] positionRequirementsList=positionRequirements.split("\n|\r");
            for (String s : positionRequirementsList) {
                newPositionRequirements = newPositionRequirements+"<p>"+s+"</P>";
            }
            map.put("positionRequirements",newPositionRequirements);
            String newPositionResponsibilities = "";
            String positionResponsibilities = (String) map.get("positionResponsibilities");
            String[] positionResponsibilitiesList=positionResponsibilities.split("\n|\r");
            for (String s : positionResponsibilitiesList) {
                newPositionResponsibilities = newPositionResponsibilities+"<p>"+s+"</P>";
            }
            map.put("positionResponsibilities",newPositionResponsibilities);
            String pwd = (String) map.get("pwd");
            if (pwd != null && !"".equals(pwd)){
                try {
                    map.put("pwd",RSAUtils.encryptedDataOnJava(pwd, publicKey));
                } catch (Exception e) {
                    System.out.println("密码加密失败");
                    e.printStackTrace();
                }
            }
            /*String rest = HttpUtil.doPostFrom("http://127.0.0.1:9091/recruit/login","username="+map.get("userId")+"&password="+map.get("pwd"));
            JSONObject restJSON = JSONObject.parseObject(rest);
            if (203 == restJSON.getInteger("code")){
                map.put("jwtToken",restJSON.get("jwtToken"));
            }else {
                System.out.println("帐号或密码错误，跳过");
                continue;
            }*/
            ToLeadPojo toLeadPojo = JSONObject.parseObject(JSONObject.toJSONString(map),ToLeadPojo.class);
            restList.add(toLeadPojo);
        }
        return restList;
    }



    //打印excel数据
    public void readExcelData(){
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for(int i=0;i<rows;i++){
            if (i==0){
                continue;
            }
            //获取列数
            XSSFRow row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for(int j=0;j<columns;j++){
                if (j==0 || j==8){
                    continue;
                }
                XSSFCell cell = row.getCell(j);
                DataFormatter dataFormatter = new DataFormatter();
                String value = dataFormatter.formatCellValue(cell);
                System.out.print(value+"@//@");
            }
            System.out.println();
        }
    }



    //测试方法
    public static void main(String[] args){
        ExcelData sheet1 = new ExcelData("/Users/weikaimo/Desktop/招聘网站数据割接.xlsx", "Sheet1");
        List<ToLeadPojo> list = sheet1.getCellToPojoList();
        /*List<Map<String,Object>> list = sheet1.getCellToList();
        Map<String,Object> map = list.get(0);
        System.out.println(JSONObject.toJSONString(map));*/
        //String cell = sheet1.getExcelDateByIndex(1, 1);
        //根据第3列值为“customer23”的这一行，来获取该行第2列的值
        //String cell3 = sheet1.getCellByCaseName("customer23", 2,1);
        //System.out.println(cell);
        //System.out.println(cell3);
    }
}

