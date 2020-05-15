package SYL.App;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.fix.aggregating.profiler.AggregatingProfiler;
import ru.fix.aggregating.profiler.ProfiledCallReport;
import ru.fix.aggregating.profiler.Profiler;
import ru.fix.aggregating.profiler.ProfilerReporter;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {
    private static void scheduleExportingToInfluxDB(InfluxDB influxDB, ProfilerReporter reporter, long delayMs) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.scheduleWithFixedDelay(() -> {
            List<ProfiledCallReport> callReports = reporter.buildReportAndReset().getProfilerCallReports();

            BatchPoints batchPoints = BatchPoints
                    .database("metrics")
                    .build();

            for (ProfiledCallReport callReport : callReports) {
                Point.Builder builder = Point.measurement(callReport.getIdentity().getName())
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

                callReport.asMap().forEach(builder::addField);

                batchPoints.point(builder.build());
            }

            influxDB.write(batchPoints);
        }, 0, delayMs, TimeUnit.MILLISECONDS);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/ui/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public Profiler profiler(InfluxDB influxDB) {
        Profiler profiler = new AggregatingProfiler();
        ProfilerReporter profilerReporter = profiler.createReporter();

        scheduleExportingToInfluxDB(influxDB, profilerReporter, 2000);

        return profiler;
    }

    @Bean
    public InfluxDB influxDB() {
        String influxDBURL = "http://localhost:8086";
        String influxDBUsername = "root";
        String influxDBPassword = "root";
        String influxDBDatabaseName = "metrics";

        InfluxDB influxDB = InfluxDBFactory.connect(influxDBURL, influxDBUsername, influxDBPassword);

        try {
            if (!influxDB.databaseExists(influxDBDatabaseName)) {
                influxDB.createDatabase(influxDBDatabaseName);
                influxDB.createRetentionPolicy("defaultPolicy",
                        "metrics",
                        "30d",
                        1,
                        true
                );
            }
        } catch (Exception e) {
            System.out.println("Something wrong with InfluxDB while create database");
        }

        return influxDB;
    }
}