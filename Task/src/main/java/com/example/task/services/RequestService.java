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
        TaskAlg taskAlg = new TaskAlg(stopWordsRepository);
        requestRepository.save(taskAlg.algAndRetRequest(str1,str2));
    }


}
