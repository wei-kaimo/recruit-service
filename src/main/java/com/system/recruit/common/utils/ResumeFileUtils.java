package com.system.recruit.common.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/1 15:53
 */
public class ResumeFileUtils {

    /**
     * @param filePath
     *            文件路径
     * @return 读出的Word的内容
     */
    public static String getTextFromWord(String filePath) {
        String result = null;
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            WordExtractor wordExtractor = new WordExtractor(fis);
            //XWPFDocument xwpfDocument = new XWPFDocument(fis);
            //HWPFDocument hwpfDocument = new HWPFDocument(fis);
            result = wordExtractor.toString();
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        return result;
    }

    /*
     * @param filePath 文件路径
     *
     * @return 读出的Excel的内容
     */
    public String getTextFromExcel(String filePath) {
        StringBuffer buff = new StringBuffer();
        try {
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            // 创建对工作表的引用。
            for (int numSheets = 0; numSheets < wb.getNumberOfSheets(); numSheets++) {
                if (null != wb.getSheetAt(numSheets)) {
                    HSSFSheet aSheet = wb.getSheetAt(numSheets);// 获得一个sheet
                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                        if (null != aSheet.getRow(rowNumOfSheet)) {
                            HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行
                            for (int cellNumOfRow = 0; cellNumOfRow <= aRow
                                    .getLastCellNum(); cellNumOfRow++) {
                                if (null != aRow.getCell(cellNumOfRow)) {
                                    HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                                    switch (aCell.getCellType()) {
                                        case FORMULA:
                                            break;
                                        case NUMERIC:
                                            buff.append(aCell.getNumericCellValue()).append('\t');
                                            break;
                                        case STRING:
                                            buff.append(aCell.getStringCellValue()).append('\t');
                                            break;
                                    }
                                }
                            }
                            buff.append('\n');
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }
    private static String readDoc (String filePath) throws Exception {
        File file = new File(filePath);
        String text= "";
        FileInputStream fis = new FileInputStream(file);
        InputStream is = FileMagic.prepareToCheckMagic(fis);
        try {
            if (FileMagic.valueOf(is) == FileMagic.OLE2) {
                WordExtractor ex = new WordExtractor(is);
                text = ex.getText();
                ex.close();
            } else if(FileMagic.valueOf(is) == FileMagic.OOXML) {
                XWPFDocument doc = new XWPFDocument(is);
                XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
                text = extractor.getText();
                extractor.close();
            }else if (FileMagic.valueOf(is) == FileMagic.UNKNOWN){
                byte[] buffer = new byte[1000];
                int n;
                while ((n = is.read(buffer)) != -1) { // 读取到缓冲区
                    //System.out.println("read " + n + " bytes.");
                    String s = new String(buffer, "utf-8");
                    System.out.println(s);
                }
            }
        } catch (OfficeXmlFileException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }
    public static void main(String[] args) throws Exception {
        System.out.println(getTextFromWord("/Users/weikaimo/Desktop/text/51Job/51job_关小强_芯片分析工程师(309306578).doc"));
    }
}
