package uz.zazu.king.common.config.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;

@Configuration
public class LogbookConfiguration {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .sink(new DefaultSink(
                        new CustomLogFormatter(),
                        new DefaultHttpLogWriter()
                ))
                .build();
    }
}
