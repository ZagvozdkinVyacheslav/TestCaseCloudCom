package com.example.task.services;


import com.example.task.algorithm.TaskAlg;
import com.example.task.repository.RequestRepository;
import com.example.task.entyties.PostRequest;

import com.example.task.repository.StopWordsRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.stachek66.nlp.mystem.holding.MyStemApplicationException;


@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final StopWordsRepository stopWordsRepository;


    public RequestService(RequestRepository requestRepository, StopWordsRepository stopWordsRepository) {
        this.requestRepository = requestRepository;

        this.stopWordsRepository = stopWordsRepository;
    }

    @SneakyThrows
    public void add(String str1, String str2)  {
        requestRepository.save(algAndRetRequest(str1,str2));
    }
    public PostRequest algAndRetRequest(String str1, String str2) throws MyStemApplicationException {
        TaskAlg taskAlg = new TaskAlg(stopWordsRepository);

        String algForStr1 = taskAlg.Algorithm(str1);
        String algForStr2 = taskAlg.Algorithm(str2);
        return new PostRequest(algForStr1, algForStr2, taskAlg.outerValueAlg(algForStr1, algForStr2));
    }

}
