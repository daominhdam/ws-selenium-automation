package com.williamssonoma.automationCore.listeners.testStep;

import java.util.Collection;

public interface TestStepCompositer extends TestStepCaller {
    Collection<TestStep> getSteps();
}
