package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class App {
    boolean inv;
    boolean ic;
    boolean rg;
    String str;
    String inputName;

    App(boolean inv, boolean ic, boolean rg, String str, String inputName) {
        this.inv = inv;
        this.ic = ic;
        this.rg = rg;
        this.str = str;
        this.inputName = inputName;
    }

    public List<String> search() {
        List<String> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputName));
            Pattern pattern1 = Pattern.compile(str);
            Pattern pattern2 = Pattern.compile(str.toLowerCase());
            List<String> readLines = br.lines().toList();
            boolean flag;
            for (String line: readLines) {
                if (ic)
                    flag = pattern2.matcher(line.toLowerCase()).find();
                else
                    flag = pattern1.matcher(line).find();
                if (inv) {
                    if (!flag)
                        res.add(line);
                }
                else {
                    if (flag)
                        res.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
