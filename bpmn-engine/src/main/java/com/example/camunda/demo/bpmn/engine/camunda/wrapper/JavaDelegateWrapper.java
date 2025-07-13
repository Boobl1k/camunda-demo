package com.example.camunda.demo.bpmn.engine.camunda.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public abstract class JavaDelegateWrapper implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    final var execution = new DelegateExecutionWrapper(delegateExecution);
    try {
      log.debug(
          "Executing delegate {}. Process instance id: {}",
          this.getClass().getSimpleName(),
          execution.getProcessInstanceId()
      );
      this.execute(execution);
      log.debug(
          "Delegate {} execution ended. Process instance id: {}",
          this.getClass().getSimpleName(),
          execution.getProcessInstanceId()
      );
    } catch (BpmnError bpmnError) {
      log.warn(
          "BPMN error while executing delegate {}. Process instance id: {}",
          this.getClass().getSimpleName(),
          execution.getProcessInstanceId(),
          bpmnError
      );
      throw bpmnError;
    }
  }

  protected abstract void execute(DelegateExecutionWrapper execution) throws Exception;
}
