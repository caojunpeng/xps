package com.cao.xps.common.io;

import java.io.File;
/**
 * File类中过滤器使用
 */
public class FileFilterText {

    public static void main(String[] args) {
        File file = new File("D:\\FileText");
        File[] files = file.listFiles(
                /*new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return false;
                    }
                }*/
               /* f -> {
                    if (f.getName().toUpperCase().endsWith(".TXT")) {
                        return true;
                    }
                    return false;
                }*/
                f -> f.getName().toUpperCase().endsWith(".BAT")
        );
        for (File file1:files) {
            System.out.println(file1.getName());
        }
    }

}
