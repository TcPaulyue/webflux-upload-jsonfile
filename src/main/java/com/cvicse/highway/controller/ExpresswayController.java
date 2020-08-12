package com.cvicse.highway.controller;


import com.alibaba.fastjson.JSONObject;
import com.cvicse.highway.domain.ExpresswayTollInfo;
import com.cvicse.highway.repository.ExpresswayRepository;
import com.cvicse.highway.service.ExpresswayService;
import org.springframework.data.mongodb.gridfs.ReactiveGridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ExpresswayController {

    private final ExpresswayRepository expresswayRepository;

    private final ExpresswayService expresswayService;

    private final ReactiveGridFsTemplate gridFsTemplate;

    public ExpresswayController(ExpresswayRepository expresswayRepository, ExpresswayService expresswayService, ReactiveGridFsTemplate gridFsTemplate) {
        this.expresswayRepository = expresswayRepository;
        this.expresswayService = expresswayService;
        this.gridFsTemplate = gridFsTemplate;
    }

    @GetMapping("/tweets")
    public Flux<ExpresswayTollInfo> getAllInfo() {
        return expresswayRepository.findAll();
    }

    @PostMapping("/tweets")
    public Mono<ExpresswayTollInfo> createExpresswayInfo(@RequestBody JSONObject content) {
        ExpresswayTollInfo expresswayTollInfo = new ExpresswayTollInfo(content);
        return expresswayRepository.save(expresswayTollInfo);
    }

//    @PostMapping(value = "/upload-mono", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.OK)
//    public Mono<List<String>> upload(@RequestPart("file") Mono<FilePart> filePartMono) {
//        /*
//          To see the response beautifully we are returning strings as Mono List
//          of String. We could have returned Flux<String> from here.
//          If you are curious enough then just return Flux<String> from here and
//          see the response on Postman
//         */
//        return expresswayService.getLines(filePartMono).collectList();
//    }

    @PostMapping(value = "/upload-filePart", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<String> upload(@RequestPart("file") FilePart filePart) {

        /*f
          To see the response beautifully we are returning strings as Mono List
          of String. We could have returned Flux<String> from here.
          If you are curious enough then just return Flux<String> from here and
          see the response on Postman
         */
        return expresswayService.getLines(filePart);
    }


    @PostMapping("")
    public Mono<String> upload(@RequestPart("file") Mono<FilePart> fileParts) {
        return fileParts
                .flatMap(part -> this.gridFsTemplate.store(part.content(), part.filename()))
                .map(objectId -> "ok");
    }
}
