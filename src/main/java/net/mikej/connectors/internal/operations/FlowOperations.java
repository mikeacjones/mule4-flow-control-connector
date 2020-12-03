package net.mikej.connectors.internal.operations;

import net.mikej.connectors.api.FlowState;
import org.mule.runtime.api.artifact.Registry;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.core.api.construct.Flow;
import org.mule.runtime.core.api.lifecycle.LifecycleState;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.reference.FlowReference;

import javax.inject.Inject;

public class FlowOperations {

    @Inject
    private Registry muleRegistry;

    public void StopFlow(
            @DisplayName("Flow Name") @FlowReference String flowName
    ) throws MuleException {
        SetFlowState(flowName, FlowState.STOPPED);
    }

    public void StartFlow(
            @DisplayName("Flow Name") @FlowReference String flowName
    ) throws MuleException {
        SetFlowState(flowName, FlowState.STARTED);
    }

    public FlowState GetFlowState(
            @DisplayName("Flow Name") @FlowReference String flowName
    ) throws Exception {
        Flow flow = (Flow) muleRegistry.lookupByName(flowName).get();
        LifecycleState currentState = flow.getLifecycleState();
        if (currentState.isStarted() || currentState.isStarting()) return FlowState.STARTED;
        else if (currentState.isStopped() || currentState.isStopping()) return FlowState.STOPPED;

        throw new Exception("Unknown flow state encountered");
    }

    public void SetFlowState(
            @DisplayName("Flow Name") @FlowReference String flowName,
            @DisplayName("State")FlowState state
    ) throws MuleException {
        Flow flow = (Flow) muleRegistry.lookupByName(flowName).get();
        LifecycleState currentState = flow.getLifecycleState();

        if (state == FlowState.STARTED) {
            if (currentState.isStarted() || currentState.isStarting()) return;
            flow.start();
        }
        else {
            if (currentState.isStopped() || currentState.isStopped()) return;
            flow.stop();
        }
    }

}