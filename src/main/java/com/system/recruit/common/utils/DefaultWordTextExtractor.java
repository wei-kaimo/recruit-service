package com.system.recruit.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/1 17:06
 */
public class DefaultWordTextExtractor {
    /** Extensions this extractor can extract text for. 此提取器可以提取文本的扩展名 */
    private Set<String> acceptableFileTypeExtensions = new HashSet<String>();

    /**  Logger  *//*
    private static final Logger log = LogManager.getLogger(DefaultWordTextExtractor.class);*/

    /**Maximum size of file this extractor will try to extract 此提取程序将尝试提取的最大文件大小*/
    private long maxFileExtractSizeInBytes = 6000000l;

    /**
     * Default constructor
     */
    public DefaultWordTextExtractor()
    {
        acceptableFileTypeExtensions.add("doc");
    }

    /**
     * Return the file type extensions this extractor can handle 返回此提取器可以处理的文件类型扩展名
     */
    public Set<String> getAcceptableFileTypeExtensions() {
        return Collections.unmodifiableSet(acceptableFileTypeExtensions);
    }


    /**
     * Extract text from a word 97-2003 document. 从Word 97-2003文档中提取文本。
     * @throws Exception
     */
    public String getText(File f) throws Exception {

        String text = null;
        if( isFileTooLarge(f) || f.length() <= 0l)
        {
            return text;
        }

        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(f);
            HWPFDocument wordDocument = new HWPFDocument(inputStream);
            WordExtractor wordExtractor = new WordExtractor(wordDocument);


            String myText = wordExtractor.getText();
            if( myText != null && !myText.trim().equals(""))
            {
                text = myText;
            }
        }
        catch(OutOfMemoryError oome)
        {
            text = null;
            //log.error("could not extract text", oome);
            System.out.println("could not extract text"+oome);
            throw(oome);
        }
        catch(Exception e)
        {
            text = null;
            //log.error("could not get text for word document " + f.getAbsolutePath(), e);
            System.out.println("could not get text for word document " + f.getAbsolutePath()+ e);
            throw(e);
        }

        finally
        {
            closeInputStream(inputStream);
        }
        return text;
    }

    /**
     * Return true if this can handle the specified extension. 如果可以处理指定的扩展名，则返回true。
     *
     */
    public boolean canExtractText(String extension) {
        return acceptableFileTypeExtensions.contains(extension);
    }

    /**
     * Returns true if this can extract text from from the file. 如果可以从文件中提取文本，则返回true。
     *
     * Simply delegates to <code>canCreateDocument(String extension)</code>
     *
     */
    public boolean canExtractText(File f) {
        return canExtractText(FilenameUtils.getExtension(f.getName())) &&
                !isFileTooLarge(f);
    }

    /**
     * Close the input stream.
     *
     * @param is - input of the stream to close
     */
    private void closeInputStream(InputStream is)
    {
        try
        {
            if( is != null)
            {
                is.close();
                is = null;
            }
        }
        catch(Exception e)
        {
            //log.error("could not close stream ", e);
            System.out.println("could not close stream "+e);
            is = null;
        }

    }

    public long getMaxFileExtractSizeInBytes() {
        return maxFileExtractSizeInBytes;
    }

    public void setMaxFileExtractSizeInBytes(long maxFileExtractSizeInBytes) {
        this.maxFileExtractSizeInBytes = maxFileExtractSizeInBytes;
    }

    /**
     * Returns true if the file is too large to convert.
     *
     * @param f - file to convert.
     * @return
     */
    public boolean isFileTooLarge(File f)
    {
        return f.length() > maxFileExtractSizeInBytes;
    }

}
