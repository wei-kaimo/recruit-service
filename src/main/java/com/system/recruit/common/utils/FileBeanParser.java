package com.system.recruit.common.utils;

import com.monitorjbl.xlsx.StreamingReader;
import com.system.recruit.entity.HrResume;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/1 17:20
 */
public class FileBeanParser {
    final static Logger logger = LoggerFactory.getLogger(FileBeanParser.class);

    public static FileBean getFileBean(File file) throws Exception {
        FileBean fileBean = new FileBean();
        String filepath = file.getAbsolutePath();
        String content;
        InputStream is = getInputStream(filepath);
        content = getContent(filepath, is);
        fileBean.setFilepath(filepath);
        fileBean.setLastModified(file.lastModified());
        fileBean.setContent(content);
        return fileBean;
    }

    private static InputStream getInputStream(String filepath) throws Exception {
        return new FileInputStream(filepath);
    }

    private static String getContent(String filepath, InputStream is) throws Exception {
        String content = "";

        if (filepath.endsWith(".doc") || filepath.endsWith(".docx")) {
            content = readDoc(filepath, is);
        } else if (filepath.endsWith(".xls") || filepath.endsWith(".xlsx")) {
            content = readExcel(is);
        } else if (filepath.endsWith(".pdf")) {
            content = readPdf(is);
        } else if (filepath.endsWith(".txt")) {
            content = readTxt(is);
        }
        return content;
    }

    public static FileMagic getFileMagic(InputStream is) throws IOException {
        is = FileMagic.prepareToCheckMagic(is);
        return FileMagic.valueOf(is);
    }

    @SuppressWarnings("deprecation")
    private static String readExcel(InputStream inp) throws Exception {
        Workbook wb;
        StringBuilder sb = new StringBuilder();
        boolean isXls = false;
        if (getFileMagic(inp) == FileMagic.OLE2) {
            wb = new HSSFWorkbook(inp);
            isXls = true;
        } else {
            wb = StreamingReader.builder()
                    .rowCacheSize(1000)    // number of rows to keep in memory (defaults to 10)
                    .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                    .open(inp);            // InputStream or File for XLSX file (required)
        }
        sb = readSheet(wb, sb, isXls);
        wb.close();
        if (inp != null) {
            inp.close();
        }
        return sb.toString();
    }

    @SuppressWarnings({"static-access", "deprecation"})
    private static StringBuilder readSheet(Workbook wb, StringBuilder sb, boolean isXls) throws Exception {
        for (Sheet sheet : wb) {
            for (Row r : sheet) {
                for (Cell cell : r) {
                    if (cell.getCellType() == STRING) {
                        sb.append(cell.getStringCellValue());
                        sb.append(" ");
                    } else if (cell.getCellType() == NUMERIC) {
                        if (isXls) {
                            DataFormatter formatter = new DataFormatter();
                            sb.append(formatter.formatCellValue(cell));
                        } else {
                            sb.append(cell.getStringCellValue());
                        }
                        sb.append(" ");
                    }
                }
            }
        }
        return sb;
    }

    private static String readDoc(String filePath, InputStream is) throws Exception {
        String text = "";
        is = FileMagic.prepareToCheckMagic(is);
        try {
            if (FileMagic.valueOf(is) == FileMagic.OLE2) {
                WordExtractor ex = new WordExtractor(is);
                text = ex.getText();
                ex.close();
            } else if (FileMagic.valueOf(is) == FileMagic.OOXML) {
                XWPFDocument doc = new XWPFDocument(is);
                XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
                text = extractor.getText();
                extractor.close();
            } else if (FileMagic.valueOf(is) == FileMagic.UNKNOWN) {
                text = "无法识别的文件类型";
            }
        } catch (OfficeXmlFileException e) {
            logger.error(filePath, e);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return text;
    }

    private static String readTxt(InputStream is) throws Exception {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(is);
        while (s.hasNext()) {
            sb.append(s.next());
        }
        if (is != null) {
            is.close();
        }
        return sb.toString();
    }

    private static String readPdf(InputStream is) throws Exception {
        String result;
        PDDocument doc = PDDocument.load(is);
        PDFTextStripper stripper = new PDFTextStripper();
        result = stripper.getText(doc);
        if (doc != null) {
            doc.close();
        }
        if (is != null) {
            is.close();
        }
        return result;
    }

    private static List<String> getPhone(String testString) {
        char[] chars = testString.toCharArray();
        ArrayList<String> charsList = new ArrayList<>();//所有11位字符
        for (int i = 0; i < chars.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 11; j++) {
                if (i + j < chars.length) {
                    stringBuilder.append(chars[i + j]);
                }
            }
            if (stringBuilder.length() == 11) {
                charsList.add(stringBuilder.toString());
            }
        }
        List<String> phoneList = new ArrayList<>();
        String str = "^0\\d{2,3}-?\\d{7,8}$|^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\\d{8}$";
        for (String s : charsList) {
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(s);
            if (m.find()) {
                System.out.println(s);
                phoneList.add(s);
            }
        }
        return phoneList;
    }

    public static void main(String[] args) throws Exception {

        FileBean fileBean = getFileBean(new File("/Users/weikaimo/Desktop/text/昊亦源-Java-魏熙美.docx"));
        String content = fileBean.getContent();
        String filepath = fileBean.getFilepath();
        long lastModified = fileBean.getLastModified();
        logger.info("filepath" + filepath + "\n");
        logger.info("lastModified" + lastModified + "\n");
        logger.info("content\n" + content + "\n");
        unifyDocumentParsing(content);
    }

    private static HrResume unifyDocumentParsing(String documentString) {
        HrResume hrResume = new HrResume();
        List<String> nameSign = new ArrayList<>();
        nameSign.add("姓名");
        nameSign.add("姓 名");
        nameSign.add("姓  名");
        nameSign.add("姓   名");
        nameSign.add("姓    名");
        List<String> sexSign = new ArrayList<>();
        sexSign.add("性别");
        sexSign.add("性 别");
        sexSign.add("性  别");
        sexSign.add("性   别");
        sexSign.add("性    别");
        List<String> ageSign = new ArrayList<>();
        ageSign.add("出身年月");
        ageSign.add("年龄");
        ageSign.add("年 龄");
        ageSign.add("年  龄");
        ageSign.add("年   龄");
        ageSign.add("年    龄");
        ageSign.add("年     龄");
        List<String> nativePlaceSign = new ArrayList<>();
        nativePlaceSign.add("家庭住址");
        nativePlaceSign.add("住址");
        nativePlaceSign.add("住 址");
        nativePlaceSign.add("住  址");
        nativePlaceSign.add("住   址");
        nativePlaceSign.add("住    址");
        nativePlaceSign.add("住     址");
        nativePlaceSign.add("籍贯");
        nativePlaceSign.add("籍 贯");
        nativePlaceSign.add("籍  贯");
        nativePlaceSign.add("籍   贯");
        nativePlaceSign.add("籍    贯");
        nativePlaceSign.add("籍     贯");
        List<String> phoneNumberSign = new ArrayList<>();
        phoneNumberSign.add("联系电话");
        List<String> yearsOfWorkingSign = new ArrayList<>();
        yearsOfWorkingSign.add("工作经验");
        List<String> eMailSign = new ArrayList<>();
        eMailSign.add("E-mail");
        List<String> educationSign = new ArrayList<>();
        educationSign.add("学历");
        String name = getContinuousStr(nameSign,documentString);
        String sex = getContinuousStr(sexSign,documentString);
        String age = getContinuousStr(ageSign,documentString);
        String nativePlace = getContinuousStr(nativePlaceSign,documentString);
        String phoneNum = getContinuousStr(phoneNumberSign,documentString);
        String yearsOfWorking = getContinuousStr(yearsOfWorkingSign,documentString);
        String eMail = getContinuousStr(eMailSign,documentString);
        String education = getContinuousStr(educationSign,documentString);
        logger.info(eMail);

        return null;
    }

    private static String getContinuousStr(List<String> deployList,  String Str){
        for (String s : deployList) {
            int index = Str.indexOf(s);
            if (index != -1) {
                String output = Str.substring(index + s.length(), index + s.length() + 100);
                output = output.replace(":","");
                output = output.replace("：","");
                char[] outputChar = output.toCharArray();
                output = "";
                int n = 0;
                for (int i = 0; i < outputChar.length; i++) {
                    if (outputChar[i] != ' ' && outputChar[i] != '\n' && outputChar[i] != '\t' && outputChar[i] != '\r') {
                        output = output+outputChar[i];
                        n++;
                    }else if (n>0){
                        break;
                    }
                }
                return output;
            }
        }
        return "";
    }
}