package org.example.clickhouse;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MetricMybatisPO {

    private String uniqueId;

    private String resourceSchemaUrl;

    private String[] resourceAttributesKey;

    private String[] resourceAttributesValue;

    private String scopeSchemaUrl;

    private String scopeName;

    private String scopeVersion;
    /**
     * 指标名称
     **/
    private String metricName;
    /**
     * 指标描述
     **/
    private String metricDescription;
    /**
     * 指标度量单位
     **/
    private String metricUnit;
    /**
     * 服务名称
     **/
    private String applicationName;

    /**
     * 实例Id
     **/
    private Long instanceId;

    /**
     * 指标数据：Gauge, Sum, Histogram, Summary, ...
     **/
    private String metricDataCase;
    /**
     * 指标参数
     **/
    private String[] metricAttributesKey;

    private String[] metricAttributesValue;
    /**
     * 聚合结束时间
     **/
    private Long timeUnixNano;
    /**
     * 聚合开始时间
     **/
    private Long startTimeUnixNano;
    /**
     * 指标值
     **/
    private BigDecimal metricValue;

    private BigDecimal[] exemplarValue;

    private String[] exemplarFilteredAttributes;

    private Integer[] exemplarFilteredAttributesCount;

    private String[] exemplarTraceId;

    private String[] exemplarSpanId;

    private Long[] exemplarTimeUnixNano;
    /**
     * true：单调的
     **/
    private Boolean monotonic;
    /**
     * 自上次报告或固定开始时间累计变化
     **/
    private String aggregationTemporality;
    /**
     * 指标数据计数
     **/
    private Long dataPointCount;
    /**
     * 指标数据计数总和
     **/
    private BigDecimal dataPointSum;
    /**
     * 指标数据在开始至结束时间最小值
     **/
    private BigDecimal dataPointMin;
    /**
     * 指标数据在开始至结束时间最大值
     **/
    private BigDecimal dataPointMax;
    /**
     * 边界；例直方图上边界
     **/
    private BigDecimal[] explicitBoundsList;
    /**
     * bucket直方图计数值
     **/
    private Long[] bucketCountsList;
    /**
     * 指数桶计数的负范围
     **/
    private BigDecimal[] negativeBucketListLeftValue;

    private BigDecimal[] negativeBucketListRightValue;

    private Long[] negativeBucketListCount;
    /**
     * 零桶数量
     **/
    private Long zeroCount;
    /**
     * 指数桶计数的正范围
     **/
    private BigDecimal[] positiveBucketListLeftValue;

    private BigDecimal[] positiveBucketListRightValue;

    private Long[] positiveBucketListCount;
    /**
     * 分位数
     **/
    private BigDecimal[] quantileListQuantile;

    private BigDecimal[] quantileListValue;

    private String namespace;

    private Long enterpriseId;
}
