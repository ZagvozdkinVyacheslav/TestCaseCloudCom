package com.example.task.services;



import com.example.task.algorithm.OutValue;
import com.example.task.algorithm.TaskAlg;
import com.example.task.entyties.StopWord;
import com.example.task.repository.RequestRepository;
import com.example.task.entyties.PostRequest;

import com.example.task.repository.StopWordsRepository;
import com.example.task.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class RequestService {
    @Autowired
    UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final StopWordsRepository stopWordsRepository;


    public RequestService(RequestRepository requestRepository, StopWordsRepository stopWordsRepository) {
        this.requestRepository = requestRepository;

        this.stopWordsRepository = stopWordsRepository;
    }

    @SneakyThrows
    public void add(Authentication auth,  String str1, String str2)  {

        requestRepository.save(algAndRetRequest(auth,str1,str2));
    }
    public PostRequest algAndRetRequest(Authentication auth, String str1, String str2) throws MyStemApplicationException {

        TaskAlg taskAlg = new TaskAlg(stopWordsRepository);
        OutValue outerValue = new OutValue();
        String algForStr1 = taskAlg.Algorithm(str1);
        String algForStr2 = taskAlg.Algorithm(str2);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd 'time:' hh:mm:ss a");
        return new PostRequest(userRepository.findUserByUsername(auth.getName()).getId()
                ,algForStr1, algForStr2, outerValue.outerValueAlg(algForStr1, algForStr2)
                ,"Created. date:" + formatForDateNow.format(dateNow));
    }
    public void checkBd(){
        if(stopWordsRepository.count() == 0) addStopWordsToBD();
    }
    private void addStopWordsToBD(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/StopWords"));
            String line = br.readLine();
            while (line != null) {
                stopWordsRepository.save(new StopWord(line));
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        } finally {

        }
    }
    public void deleteBd(Authentication auth){
        requestRepository.deleteAllByUserId(userRepository.findUserByUsername(auth.getName()).getId());
    }





}
