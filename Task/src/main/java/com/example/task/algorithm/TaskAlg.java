package com.example.task.algorithm;

import com.example.task.entyties.PostRequest;
import com.example.task.repository.StopWordsRepository;
import com.example.task.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import ru.stachek66.nlp.mystem.holding.Factory;
import ru.stachek66.nlp.mystem.holding.MyStem;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;
import ru.stachek66.nlp.mystem.holding.Request;
import ru.stachek66.nlp.mystem.model.Info;
import scala.Option;
import scala.collection.JavaConversions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TaskAlg {


    private final StopWordsRepository stopWordsRepository;
    private OutValue outerValue = new OutValue();
    public TaskAlg(StopWordsRepository stopWordsRepository) {
        this.stopWordsRepository = stopWordsRepository;
    }
    private final MyStem mystemAnalyzer =
            new Factory("-igd --eng-gr --format json --weight")
                    .newMyStem("3.0", Option.apply(new File("mystem.exe"))).get();

    public String Algorithm(String str) throws MyStemApplicationException {
        return Lemmitization(deleteStopWords(str));
    }

    private String deleteStopWords(String inputStr){
        inputStr = inputStr.replaceAll("\\p{Punct}", "");
        StringBuilder retStr = new StringBuilder();
        boolean fl;
        String[] splStr = inputStr.toLowerCase().split(" ");

        for (String str:splStr) {
            fl = true;
            for (long j = 1; j <= stopWordsRepository.count(); j++) {
                if(str.equals(stopWordsRepository.findById(j).get().getWord()))
                {
                    fl = false;
                    break;
                }
            }
            if(fl) retStr.append(str + " ");
        }
        retStr.deleteCharAt(retStr.length() - 1);
        return retStr.toString();

    }
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

}
