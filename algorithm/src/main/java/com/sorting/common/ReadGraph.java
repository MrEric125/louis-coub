package com.sorting.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Eric
 * @date create in 2018/12/1
 */
public class ReadGraph {
    private Scanner scanner;

    public ReadGraph(BaseGraph graph,String filename) {
        readFile(filename);
        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalAccessException("number of edges in a");
            }
            assert V == graph.V();

            int E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        }
        catch (InputMismatchException | IllegalAccessException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    private void readFile(String filename) {
        assert filename != null;
        File file = new File(filename);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new IllegalAccessException(filename + "doesn't exist");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
