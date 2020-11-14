package com.effectivejava.rule;

import java.io.*;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
public class TryResource {

    static String firstLineOfFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        try {
            return reader.readLine();
        }finally {
            reader.close();

        }
    }

    static void copy(String src, String dst) throws IOException {

        InputStream in = new FileInputStream(src);
        try {
            FileOutputStream outputStream = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[1024];
                int n = 0;
                while ((n = in.read(buf)) >= 0) {
                    outputStream.write(buf, 0, n);
                }
            }finally {
                outputStream.close();
            }
        }finally {
            in.close();
        }
    }


    static void copy2(String src, String dst) throws IOException {

        try( InputStream in = new FileInputStream(src);
             FileOutputStream outputStream = new FileOutputStream(dst);
        ) {
                byte[] buf = new byte[1024];
                int n = 0;
                while ((n = in.read(buf)) >= 0) {
                    outputStream.write(buf, 0, n);
                }
        }
    }
}
