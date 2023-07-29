package com.example.task.algorithm;

import com.example.task.repository.StopWordsRepository;
import lombok.NoArgsConstructor;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskAlg {
    private final StopWordsRepository stopWordsRepository;
    private OutValue outerValue = new OutValue();
    public TaskAlg(StopWordsRepository stopWordsRepository) {
        this.stopWordsRepository = stopWordsRepository;
    }


    public String Algorithm(String str) throws MyStemApplicationException {
        return Lemmitization(deleteStopWords(str));
    }

    private String deleteStopWords(String inputStr){

        String lowerCaseStr = inputStr.toLowerCase();//перевод в нижний регистр
        StringBuilder retStr = new StringBuilder();
        ArrayList<Integer> indexToDel = new ArrayList<>();
        String[] splStr = lowerCaseStr.split(" ");

        for (int i = 0; i < splStr.length; i++) {//пишем индыксы стоп слов в строке
            for (long j = 1; j <= stopWordsRepository.count(); j++) {
                if(splStr[i].equals(stopWordsRepository.findById(j).get().getWord())){
                    splStr[i] = "";
                    indexToDel.add(i);
                    break;
                }
            }
        }
        for (int i = 0; i < splStr.length; i++) {//собираем новую строку
            for (int j = 0; j < indexToDel.size(); j++) {
                if(i != indexToDel.get(j)){
                    retStr.append(splStr[i]);
                    if(i != splStr.length - 1) retStr.append(" ");
                    break;
                }
            }

        }
        return retStr.toString();

    }
    private final MyStem mystemAnalyzer =
            new Factory("-igd --eng-gr --format json --weight")
                    .newMyStem("3.0", Option.apply(new File("mystem.exe"))).get();

    private String Lemmitization(String inputStr) throws MyStemApplicationException {
        StringBuilder sb = new StringBuilder();

        final Iterable<Info> result =
                JavaConversions.asJavaIterable(
                        mystemAnalyzer
                                .analyze(Request.apply(inputStr))
                                .info()
                                .toIterable());

        for (final Info info : result) {
            StringBuilder remover = new StringBuilder(info.lex().toString());
            remover.delete(0,5);
            remover.deleteCharAt(remover.length() - 1);
            sb.append(remover + " ");
        }
        if(sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    public String outerValueAlg(String str1,String str2){
        return outerValue.outerValueAlg(str1,str2);
    }

}
