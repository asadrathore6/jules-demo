package com.example.weatherapp;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootStreamHandler;

public class Handler extends SpringBootStreamHandler<AwsProxyRequest, AwsProxyResponse> {
}
