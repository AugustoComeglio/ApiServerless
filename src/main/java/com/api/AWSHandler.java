package com.api;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.api.ApiApplication;
import org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Map;

public class AWSHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static{
        try{
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(ApiApplication.class);
            // For applications that take longer than 10 seconds to start, use the async builder:
            // long startTime = Instant.now().toEpochMilli();
             //handler = new SpringBootProxyHandlerBuilder<>().defaultProxy()
               //      .asyncInit(startTime)
                 //    .springBootApplication(ApiApplication.class)
                   //  .buildAndInitialize();
        }catch (ContainerInitializationException e){
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application",e);
        }

    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        handler.proxyStream(input, output, context);
    }
}
