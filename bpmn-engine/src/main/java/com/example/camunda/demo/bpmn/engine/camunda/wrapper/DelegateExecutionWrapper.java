package com.example.camunda.demo.bpmn.engine.camunda.wrapper;

import java.util.Map;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;

@RequiredArgsConstructor
public class DelegateExecutionWrapper {

  private final DelegateExecution targetExecution;

  @SuppressWarnings("unchecked")
  public <T> T getVariable(ExecutionVariable<T> executionVariable) {
    return (T) targetExecution.getVariable(executionVariable.getName());
  }

  public <T> T computeIfAbsent(ExecutionVariable<T> executionVariable, Supplier<T> supplier) {
    T variable = getVariable(executionVariable);

    if (variable == null) {
      variable = supplier.get();
      setVariable(executionVariable, variable);
    }

    return variable;
  }

  public <T> void setVariable(ExecutionVariable<T> executionVariable, T value) {
    targetExecution.setVariable(executionVariable.getName(), value);
  }

  public String getProcessDefinitionId() {
    return targetExecution.getProcessDefinitionId();
  }

  public String getProcessInstanceId() {
    return targetExecution.getProcessInstanceId();
  }

  public Map<String, Object> getVariables() {
    return targetExecution.getVariables();
  }

  public <T> void removeVariable(ExecutionVariable<T> executionVariable) {
    targetExecution.removeVariable(executionVariable.getName());
  }
}
