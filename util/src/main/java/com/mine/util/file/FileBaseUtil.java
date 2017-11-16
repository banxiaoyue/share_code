package com.mine.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by haining on 16/9/20.
 */
public class FileBaseUtil {

    /**
     * 删除多余临时文件
     * @param deleteFilePath
     */
    static Logger logger = Logger.getLogger("");
    private final static int BUFFER = 1024;
    private static void deleteTempFile(String deleteFilePath){
        File file = new File(deleteFilePath);
        if (file.isDirectory()){
            throw new RuntimeException(deleteFilePath + "是一个文件夹地址不能调用该方法删除文件!");
        }
        if (file != null && file.exists()){
            file.delete();
        }
        if (file.exists()){
            throw new RuntimeException(deleteFilePath + "删除失败!");
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFileName 指定的文件全路径名
     * @param destDir 移动到指定的文件夹
     * @return
     */
    public static void copyTo(String sourceFileName, String destDir) throws IOException {
        File fileSource = new File(sourceFileName);
        File fileDest = new File(destDir);
        // 如果源文件不存或源文件是文件夹
        if (!fileSource.exists() || !fileSource.isFile()) {
            throw new RuntimeException("源文件[" + sourceFileName + "],不存在或是文件夹!");
        }
        // 如果目标文件夹不存在
        if (!fileDest.isDirectory() || !fileDest.exists()) {
            if (!fileDest.mkdirs()) {
                throw new RuntimeException("目录文件夹不存，在创建目标文件夹时失败!");
            }
        }
        String strAbsFilename = destDir + File.separator + fileSource.getName();
        if (new File(strAbsFilename).exists()){
            throw new RuntimeException("文件["+ strAbsFilename +"]已经存在!");
        }
        FileInputStream fileInput = null;
        FileOutputStream fileOutput = null;
        try {
            fileInput = new FileInputStream(sourceFileName);
            fileOutput = new FileOutputStream(strAbsFilename);
            logger.info("源文件["+ sourceFileName +"],目标文件夹["+ destDir +"]开始拷贝文件");
            int count = -1;
            byte[] data = new byte[BUFFER];
            while (-1 != (count = fileInput.read(data, 0, BUFFER))) {
                fileOutput.write(data, 0, count);
            }
            logger.info("源文件["+ sourceFileName +"],目标文件夹["+ destDir +"]拷贝文件成功!");
        } catch (Exception e) {
            throw new RuntimeException("源文件["+ sourceFileName +"],目标文件夹["+ destDir +"]复制失败!",e);
        } finally {
            if (fileInput != null) {
                fileInput.close();
            }
            if (fileOutput != null) {
                fileOutput.close();
            }
        }
    }

    /**
     * 文件重命名
     *
     * @param resouceFilePath 源文件地址(包含文件名字)
     * @param outFilePath 目标文件地址(包含文件名字)
     * @return
     */
    public static boolean renameTo(String resouceFilePath,String outFilePath){
        File resultFile = new File(resouceFilePath);
        resultFile.renameTo(new File(outFilePath));
        return true;
    }
}
