package com.cvicse.highway.service;


import com.alibaba.fastjson.JSONObject;
import com.cvicse.highway.domain.ExpresswayTollInfo;
import com.cvicse.highway.repository.ExpresswayRepository;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExpresswayServiceImpl implements ExpresswayService {
    private final ExpresswayRepository expresswayRepository;

    public ExpresswayServiceImpl(ExpresswayRepository expresswayRepository) {
        this.expresswayRepository = expresswayRepository;
    }


    @Override
    public Flux<String> getLines(Mono<FilePart> filePartMono) {
        return filePartMono.flatMapMany(this::getLines);
    }

    @Override
    public Mono<String> getLines(FilePart filePart) {
        return filePart.content().flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            String content = new String(bytes,StandardCharsets.UTF_8);
            return Mono.just(content);
        }).map(this::processAndGetLinesAsList)
                .flatMapIterable(Function.identity())
                .collectList()
                .map(this::saveFile);
    }

    private String saveFile(List<String> strings){
        String s = "";
        for(String s1:strings){
            s+= s1;
        }
        ExpresswayTollInfo expresswayTollInfo = new ExpresswayTollInfo(JSONObject.parseObject(s));
        expresswayRepository.save(expresswayTollInfo).subscribe();
        return "ok";
    }

    private List<String> processAndGetLinesAsList(String string) {

        Supplier<Stream<String>> streamSupplier = string::lines;

        return streamSupplier.get().filter(s -> !s.isBlank()).collect(Collectors.toList());
    }
}
