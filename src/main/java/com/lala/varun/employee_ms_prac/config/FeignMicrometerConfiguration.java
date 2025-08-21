package com.lala.varun.employee_ms_prac.config;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;

//By Default Feign Library may not fully propogate Micrometer tracing without this configuration.
//Otherwise it will be leading to disconnected traces in the Micrometer tracing system.


@Configuration
public class FeignMicrometerConfiguration {
    public Capability micrometerCapability(MeterRegistry meterRegistry) {
        return  new MicrometerCapability(meterRegistry);
    }
}
