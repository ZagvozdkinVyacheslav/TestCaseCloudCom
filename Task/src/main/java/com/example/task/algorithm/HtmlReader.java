package com.example.task.algorithm;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
@NoArgsConstructor
public class HtmlReader {
    public String readHtml(String path){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            br.close();
        } catch (Exception e) {
        } finally {

        }
        return sb.toString();
    }
}
