package com.louis.longagocode.concurrent.inpractice.chapter5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable{

    private final BlockingQueue<File> fileQueue=new ArrayBlockingQueue<File>(10);

    private final FileFilter fileFilter=new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return false;
        }
    };

    private final File root = new File("");

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    fileQueue.put(entry);

                }
            }
        }

    }

    private boolean alreadyIndexed(File entry) {
        return false;
    }
}
