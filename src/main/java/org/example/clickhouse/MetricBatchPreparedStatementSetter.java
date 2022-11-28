package org.example.clickhouse;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MetricBatchPreparedStatementSetter implements BatchPreparedStatementSetter {

    private AtomicLong time1 = new AtomicLong(0L);

    private AtomicLong time2 = new AtomicLong(0L);

    private final List<MetricMybatisPO> metricList;

    public MetricBatchPreparedStatementSetter(List<MetricMybatisPO> metricList) {
        this.metricList = metricList;
    }

//    @Override
//    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//        Connection connection = preparedStatement.getConnection();
//        MetricMybatisPO dto = metricList.get(i);
//        int paramIndex = 1;
//        preparedStatement.setString(paramIndex++, dto.getUniqueId());
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getResourceSchemaUrl(), String.class));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getResourceAttributesKey()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getResourceAttributesValue()));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getScopeSchemaUrl(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getScopeName(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getScopeVersion(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(String.valueOf(dto.getEnterpriseId()), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getNamespace(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getApplicationName(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getInstanceId(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMetricName(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMetricDescription(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMetricUnit(), String.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMetricDataCase(), String.class));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getMetricAttributesKey()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getMetricAttributesValue()));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getTimeUnixNano(), Long.class));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getStartTimeUnixNano(), Long.class));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMetricValue(), BigDecimal.class));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getExemplarValue()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarFilteredAttributes()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("INTEGER", dto.getExemplarFilteredAttributesCount()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarTraceId()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarSpanId()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getExemplarTimeUnixNano()));
//        preparedStatement.setBoolean(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getMonotonic(), Boolean.class));
//        preparedStatement.setString(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getAggregationTemporality(), String.class));
//        preparedStatement.setLong(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getDataPointCount(), Long.class));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getDataPointSum(), BigDecimal.class));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getDataPointMin(), BigDecimal.class));
//        preparedStatement.setObject(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getDataPointMax(), BigDecimal.class));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getExplicitBoundsList()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getBucketCountsList()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getNegativeBucketListLeftValue()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getNegativeBucketListRightValue()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getNegativeBucketListCount()));
//        preparedStatement.setLong(paramIndex++, ProtoUtil.transformNullToEmpty(dto.getZeroCount(), Long.class));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getPositiveBucketListLeftValue()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getPositiveBucketListRightValue()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getPositiveBucketListCount()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getQuantileListQuantile()));
//        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getQuantileListValue()));
//    }

    @Override
    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        Connection connection = preparedStatement.getConnection();
        MetricMybatisPO dto = metricList.get(i);
        int paramIndex = 1;
        long nanoTime1 = System.nanoTime();
        preparedStatement.setString(paramIndex++, dto.getUniqueId());
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getResourceSchemaUrl()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getResourceAttributesKey()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getResourceAttributesValue()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getScopeSchemaUrl()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getScopeName()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getScopeVersion()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(String.valueOf(dto.getEnterpriseId())));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getNamespace()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getApplicationName()));
        preparedStatement.setLong(paramIndex++, dto.getInstanceId());
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getMetricName()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getMetricDescription()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getMetricUnit()));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getMetricDataCase()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getMetricAttributesKey()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getMetricAttributesValue()));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getTimeUnixNano(), 0L));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getStartTimeUnixNano(), 0L));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getMetricValue(), BigDecimal.ZERO));
        long nanoTime2 = System.nanoTime();
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getExemplarValue()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarFilteredAttributes()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("INTEGER", dto.getExemplarFilteredAttributesCount()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarTraceId()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("VARCHAR", dto.getExemplarSpanId()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getExemplarTimeUnixNano()));
        preparedStatement.setBoolean(paramIndex++, ObjectUtil.defaultIfNull(dto.getMonotonic(), Boolean.FALSE));
        preparedStatement.setString(paramIndex++, StrUtil.nullToEmpty(dto.getAggregationTemporality()));
        preparedStatement.setLong(paramIndex++, ObjectUtil.defaultIfNull(dto.getDataPointCount(), 0L));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getDataPointSum(), BigDecimal.ZERO));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getDataPointMin(), BigDecimal.ZERO));
        preparedStatement.setObject(paramIndex++, ObjectUtil.defaultIfNull(dto.getDataPointMax(), BigDecimal.ZERO));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getExplicitBoundsList()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getBucketCountsList()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getNegativeBucketListLeftValue()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getNegativeBucketListRightValue()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getNegativeBucketListCount()));
        preparedStatement.setLong(paramIndex++, ObjectUtil.defaultIfNull(dto.getZeroCount(), 0L));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getPositiveBucketListLeftValue()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getPositiveBucketListRightValue()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("BIGINT", dto.getPositiveBucketListCount()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getQuantileListQuantile()));
        preparedStatement.setArray(paramIndex++, connection.createArrayOf("DECIMAL", dto.getQuantileListValue()));
        long nanoTime3 = System.nanoTime();

        time1.addAndGet(nanoTime2 - nanoTime1);
        time2.addAndGet(nanoTime3 - nanoTime2);
        if (i >= 999) {
            log.info("time1=" + time1.get() + "ns,time2=" + time2.get() + "ns");
            time1.set(0);
            time2.set(0);
        }
    }

    @Override
    public int getBatchSize() {
        return metricList.size();
    }
}

