package com.imatia.qsallcomponents.configuration;

import com.imatia.qsallcomponents.model.service.ExportServiceExt;
import com.imatia.qsallcomponents.ws.rest.ExportRestControllerExt;
import com.ontimize.boot.export.OExportAutoConfigure;
import com.ontimize.jee.webclient.export.base.ExportRestController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;


@Configuration
@Import({OExportAutoConfigure.class})
@ConditionalOnProperty(name = "ontimize.export.enable", havingValue = "true", matchIfMissing = false)
public class ExportAutoConfigurationExt {

//    @Bean("ExportRestController")
//    @Primary
//    public ExportRestController exportRestController() {
//        return new ExportRestControllerExt();
//    }
//
//    @Bean("CsvExportService")
//    public ExportServiceExt exportService() {
//        return new ExportServiceExt();
//    }
}
