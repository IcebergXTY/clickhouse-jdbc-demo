package org.example.clickhouse;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DemoCommandRunner implements CommandLineRunner {

    private static final String INSERT_SQL = """
            INSERT INTO telemetry_metric_origin
            (unique_id, resource_schema_url, `resource_attributes.key`, `resource_attributes.value`,
            scope_schema_url, scope_name, scope_version, enterprise_id, namespace, application_name, instance_id,
            metric_name, metric_description, metric_unit, metric_data_case, `metric_attributes.key`,
            `metric_attributes.value`, time_unix_nano, start_time_unix_nano, metric_value, `exemplar.value`,
            `exemplar.filtered_attributes`, `exemplar.filtered_attributes_count`, `exemplar.trace_id`, `exemplar.span_id`,
            `exemplar.time_unix_nano`, monotonic, aggregation_temporality, data_point_count, data_point_sum,
            data_point_min, data_point_max, explicit_bounds_list, bucket_counts_list, `negative_bucket_list.left_value`,
            `negative_bucket_list.right_value`, `negative_bucket_list.count`, zero_count, `positive_bucket_list.left_value`,
            `positive_bucket_list.right_value`, `positive_bucket_list.count`, `quantile_list.quantile`, `quantile_list.value`)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String testSql = """
            INSERT INTO telemetry_metric_origin select
            unique_id, resource_schema_url, `resource_attributes.key`, `resource_attributes.value`,
            scope_schema_url, scope_name, scope_version, enterprise_id, namespace, application_name, instance_id,
            metric_name, metric_description, metric_unit, metric_data_case, `metric_attributes.key`,
            `metric_attributes.value`, time_unix_nano, start_time_unix_nano, metric_value, `exemplar.value`,
            `exemplar.filtered_attributes`, `exemplar.filtered_attributes_count`, `exemplar.trace_id`, `exemplar.span_id`,
            `exemplar.time_unix_nano`, monotonic, aggregation_temporality, data_point_count, data_point_sum,
            data_point_min, data_point_max, explicit_bounds_list, bucket_counts_list, `negative_bucket_list.left_value`,
            `negative_bucket_list.right_value`, `negative_bucket_list.count`, zero_count, `positive_bucket_list.left_value`,
            `positive_bucket_list.right_value`, `positive_bucket_list.count`, `quantile_list.quantile`, `quantile_list.value`
             from input(
            'unique_id UUID,
            resource_schema_url LowCardinality(String),
            `resource_attributes.key` Array(LowCardinality(String)),
            `resource_attributes.value` Array(String),
            scope_schema_url LowCardinality(String),
            scope_name LowCardinality(String),
            scope_version LowCardinality(String),
            enterprise_id Int64,
            namespace LowCardinality(String),
            application_name LowCardinality(String),
            instance_id Int64,
            metric_name LowCardinality(String),
            metric_description String,
            metric_unit LowCardinality(String),
            metric_data_case LowCardinality(String),
            `metric_attributes.key` Array(LowCardinality(String)),
            `metric_attributes.value` Array(String),
            time_unix_nano DateTime64(9, ''Asia/Shanghai''),
            start_time_unix_nano DateTime64(9, ''Asia/Shanghai''),
            metric_value Decimal(18, 4),
            `exemplar.value` Array(Decimal(18, 4)),
            `exemplar.filtered_attributes` Array(String),
            `exemplar.filtered_attributes_count` Array(Int32),
            `exemplar.trace_id` Array(String),
            `exemplar.span_id` Array(String),
            `exemplar.time_unix_nano` Array(DateTime64(9, ''Asia/Shanghai'')),
            monotonic Bool,
            aggregation_temporality LowCardinality(String),
            data_point_count UInt64,
            data_point_sum Decimal(18, 4),
            data_point_min Decimal(18, 4),
            data_point_max Decimal(18, 4),
            explicit_bounds_list Array(Decimal(18, 4)),
            bucket_counts_list Array(UInt64),
            `negative_bucket_list.left_value` Array(Decimal(18, 4)),
            `negative_bucket_list.right_value` Array(Decimal(18, 4)),
            `negative_bucket_list.count` Array(UInt64),
            zero_count UInt64,
            `positive_bucket_list.left_value` Array(Decimal(18, 4)),
            `positive_bucket_list.right_value` Array(Decimal(18, 4)),
            `positive_bucket_list.count` Array(UInt64),
            `quantile_list.quantile` Array(Decimal(18, 4)),
            `quantile_list.value` Array(Decimal(18, 4))
            ')
            """;

    @Autowired
    private ClickHouseDataSourceWrapper clickHouseDataSourceWrapper;

    @Override
    public void run(String... args) throws Exception {
        JdbcTemplate classicJdbcTemplate = clickHouseDataSourceWrapper.getJdbcTemplate();
        MockConfig mockConfig = MockConfig.newInstance().sizeRange(2, 2);
        while (true) {
            List<MetricMybatisPO> list = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                MetricMybatisPO metricMybatisPO = new MetricMybatisPO();
                metricMybatisPO.setUniqueId(UUID.randomUUID().toString());
                metricMybatisPO.setResourceSchemaUrl(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setResourceAttributesKey(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setResourceAttributesValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setScopeSchemaUrl(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setScopeName(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setScopeVersion(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricName(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricDescription(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricUnit(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setApplicationName(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setInstanceId(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricDataCase(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricAttributesKey(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricAttributesValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setTimeUnixNano(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setStartTimeUnixNano(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMetricValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarFilteredAttributes(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarFilteredAttributesCount(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarTraceId(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarSpanId(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExemplarTimeUnixNano(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setMonotonic(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setAggregationTemporality(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setDataPointCount(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setDataPointSum(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setDataPointMin(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setDataPointMax(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setExplicitBoundsList(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setBucketCountsList(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setNegativeBucketListLeftValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setNegativeBucketListRightValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setNegativeBucketListCount(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setZeroCount(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setPositiveBucketListLeftValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setPositiveBucketListRightValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setPositiveBucketListCount(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setQuantileListQuantile(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setQuantileListValue(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setNamespace(JMockData.mock(new TypeReference<>() {}, mockConfig));
                metricMybatisPO.setEnterpriseId(JMockData.mock(new TypeReference<>() {}, mockConfig));
                list.add(metricMybatisPO);
            }
            classicJdbcTemplate.batchUpdate(testSql, new MetricBatchPreparedStatementSetter(list));
        }
    }
}
