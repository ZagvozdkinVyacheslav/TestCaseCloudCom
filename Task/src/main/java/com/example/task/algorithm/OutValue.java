package com.example.task.algorithm;

import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
public class OutValue {

    public String outerValueAlg(String str1,String str2){
        Set<String> setFromStr1 = new HashSet<>();
        Set<String> setFromStr2 = new HashSet<>();
        Integer count = 0;

        for (String str : str1.split(" "))
            setFromStr1.add(str);

        for (String str : str2.split(" "))
            setFromStr2.add(str);

        for (String i:setFromStr1) {
            for (String j:setFromStr2) {
                if(i.equals(j)){
                    count++;
                    break;
                }
            }
        }
        return new DecimalFormat("#.##").format((double)count / (double)setFromStr1.size() * 100) + "%";
    }
}
