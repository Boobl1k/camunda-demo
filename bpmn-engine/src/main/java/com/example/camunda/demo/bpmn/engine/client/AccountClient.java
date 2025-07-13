package com.example.camunda.demo.bpmn.engine.client;

import com.example.camunda.demo.account.service.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
    name = "account",
    url = "${app.account.client.url}"
)
public interface AccountClient extends AccountApi {
}
