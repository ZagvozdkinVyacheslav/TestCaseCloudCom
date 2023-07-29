package com.example.task.algorithm;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
public class OutValue {

    public String outerValueAlg(String str1,String str2){
        String[] strMas1 = str1.split(" ");
        String[] strMas2 = str2.split(" ");
        Set<String> setFromStr1 = new HashSet<>();
        Set<String> setFromStr2 = new HashSet<>();
        Integer count = 0;
        for (int i = 0; i < strMas1.length ; i++) {//избавляемся от повторений
            setFromStr1.add(strMas1[i]);
        }
        for (int i = 0; i < strMas2.length ; i++) {
            setFromStr2.add(strMas2[i]);
        }
        ArrayList<String> arrFromSet1 = new ArrayList<>();
        ArrayList<String> arrFromSet2 = new ArrayList<>();
        for (String x: setFromStr1) {

            arrFromSet1.add(x);
        }
        for (String x: setFromStr2) {
            arrFromSet2.add(x);
        }
        for (int i = 0; i < arrFromSet1.size(); i++) {//тк повторений в массивах нет, то используем накоп перем
            for (int j = 0; j < arrFromSet2.size(); j++) {
                if(arrFromSet1.get(i).equals(arrFromSet2.get(j))){
                    count++;
                    break;
                }
            }
        }

        if(str1.length() >= str2.length())
            return Double.toString((double)count / (double)strMas1.length * 100) + "%";
        else
            return Double.toString((double)count / (double)strMas2.length * 100) + "%";
    }
}
