package com.cvicse.vertxwithspring;

import com.alibaba.fastjson.JSONObject;
import com.cvicse.vertxwithspring.domain.ExpresswayTollInfo;
import com.cvicse.vertxwithspring.repository.ExpresswayRepository;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Set;

@SpringBootApplication
public class VertxwithspringApplication {


	private final ExpresswayRepository expresswayRepository;

	public VertxwithspringApplication(ExpresswayRepository expresswayRepository) {
		this.expresswayRepository = expresswayRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(VertxwithspringApplication.class, args);
	}


	@PostConstruct
	public void startVertx(){
		Vertx vertx = Vertx.vertx();
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.post("/upload-filePart").handler(routingContext -> {

			Set<FileUpload> uploads = routingContext.fileUploads();
			uploads.forEach(fileUpload -> {
				Buffer uploadedFile = vertx.fileSystem().readFileBlocking(fileUpload.uploadedFileName());
				ExpresswayTollInfo expresswayTollInfo = new ExpresswayTollInfo(JSONObject.parseObject(uploadedFile.toString()));
				expresswayRepository.save(expresswayTollInfo);
				System.out.println(expresswayTollInfo.toString());
			});
			// This handler gets called for each request that arrives on the server
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/plain");
			// Write to the response and end it
			response.end("ok");
		});
		server.requestHandler(router).listen(8000);
	}
}
