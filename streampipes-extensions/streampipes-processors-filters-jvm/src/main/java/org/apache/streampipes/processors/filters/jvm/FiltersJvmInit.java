/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.streampipes.processors.filters.jvm;

import org.apache.streampipes.dataformat.cbor.CborDataFormatFactory;
import org.apache.streampipes.dataformat.fst.FstDataFormatFactory;
import org.apache.streampipes.dataformat.json.JsonDataFormatFactory;
import org.apache.streampipes.dataformat.smile.SmileDataFormatFactory;
import org.apache.streampipes.extensions.management.model.SpServiceDefinition;
import org.apache.streampipes.extensions.management.model.SpServiceDefinitionBuilder;
import org.apache.streampipes.messaging.jms.SpJmsProtocolFactory;
import org.apache.streampipes.messaging.kafka.SpKafkaProtocolFactory;
import org.apache.streampipes.messaging.mqtt.SpMqttProtocolFactory;
import org.apache.streampipes.processors.filters.jvm.processor.booleanfilter.BooleanFilterProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.compose.ComposeProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.enrich.MergeByEnrichProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.limit.RateLimitProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.merge.MergeByTimeProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.movingaverage.MovingAverageProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.numericalfilter.NumericalFilterProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.numericaltextfilter.NumericalTextFilterProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.projection.ProjectionProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.schema.MergeBySchemaProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.textfilter.TextFilterProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.threshold.ThresholdDetectionProcessor;
import org.apache.streampipes.processors.filters.jvm.processor.throughputmon.ThroughputMonitorProcessor;
import org.apache.streampipes.service.extensions.ExtensionsModelSubmitter;

public class FiltersJvmInit extends ExtensionsModelSubmitter {

  public static void main(String[] args) {
    new FiltersJvmInit().init();
  }

  @Override
  public SpServiceDefinition provideServiceDefinition() {
    return SpServiceDefinitionBuilder.create("org.apache.streampipes.processors.filters.jvm",
            "StreamPipes Processors Filters (JVM)",
            "",
            8090)
        .registerPipelineElements(
            new BooleanFilterProcessor(),
            new TextFilterProcessor(),
            new NumericalFilterProcessor(),
            new ThresholdDetectionProcessor(),
            new ThroughputMonitorProcessor(),
            new ProjectionProcessor(),
            new MergeByEnrichProcessor(),
            new MergeByTimeProcessor(),
            new MergeBySchemaProcessor(),
            new ComposeProcessor(),
            new NumericalTextFilterProcessor(),
            new RateLimitProcessor(),
            new MovingAverageProcessor())
        .registerMessagingFormats(
            new JsonDataFormatFactory(),
            new CborDataFormatFactory(),
            new SmileDataFormatFactory(),
            new FstDataFormatFactory())
        .registerMessagingProtocols(
            new SpKafkaProtocolFactory(),
            new SpJmsProtocolFactory(),
            new SpMqttProtocolFactory())
        .build();
  }
}
