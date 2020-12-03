package net.mikej.connectors.internal;

import net.mikej.connectors.internal.operations.FlowOperations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

@Xml(prefix = "flow-control")
@Extension(name = "Flow Control")
@Operations({FlowOperations.class})
public class FlowControlConnector {
}
