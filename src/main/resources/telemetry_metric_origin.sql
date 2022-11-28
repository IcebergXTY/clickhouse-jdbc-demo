CREATE TABLE telemetry_metric_origin
(
    unique_id               UUID COMMENT '唯一ID' CODEC (ZSTD(1)),
    resource_schema_url     LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    resource_attributes     Nested (
        key LowCardinality(String),
        value String
        ) COMMENT '' CODEC (ZSTD(1)),
    scope_schema_url        LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    scope_name              LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    scope_version           LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    enterprise_id           Int64 COMMENT '' CODEC (ZSTD(1)),
    namespace                LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    application_name        LowCardinality(String) COMMENT '' CODEC (ZSTD(1)),
    instance_id             Int64 COMMENT '' CODEC (ZSTD(1)),
    metric_name             LowCardinality(String) COMMENT '指标名称' CODEC (ZSTD(1)),
    metric_description      String COMMENT '指标描述' CODEC (ZSTD(1)),
    metric_unit             LowCardinality(String) COMMENT '指标度量单位' CODEC (ZSTD(1)),
    metric_data_case        LowCardinality(String) COMMENT '指标类型，Gauge、Sum、Histogram、Summary' CODEC (ZSTD(1)),
    metric_attributes       Nested (
        key LowCardinality(String),
        value String
        ) COMMENT '指标参数' CODEC (ZSTD(1)),
    time_unix_nano          DateTime64(9, 'Asia/Shanghai') COMMENT '聚合结束时间' CODEC (ZSTD(1)),
    start_time_unix_nano    DateTime64(9, 'Asia/Shanghai') COMMENT '聚合开始时间' CODEC (ZSTD(1)),
    metric_value            Decimal64(4) COMMENT '指标值' CODEC (ZSTD(1)),
    exemplar                Nested (
        value Decimal64(4),
        filtered_attributes String,
        filtered_attributes_count Int32,
        trace_id String,
        span_id String,
        time_unix_nano DateTime64(9, 'Asia/Shanghai')
        ) COMMENT '关联的trace信息' CODEC (ZSTD(1)),
    monotonic               BOOL COMMENT '是否是单调的' CODEC (ZSTD(1)),
    aggregation_temporality LowCardinality(String) COMMENT '指标聚合的时间周期' CODEC (ZSTD(1)),
    data_point_count        UInt64 COMMENT '指标数据的数量' CODEC (ZSTD(1)),
    data_point_sum          Decimal64(4) COMMENT '指标数据的总和' CODEC (ZSTD(1)),
    data_point_min          Decimal64(4) COMMENT '指标数据最小值' CODEC (ZSTD(1)),
    data_point_max          Decimal64(4) COMMENT '指标数据最大值' CODEC (ZSTD(1)),
    explicit_bounds_list    Array(Decimal64(4)) COMMENT '边界，比如直方图上边界' CODEC (ZSTD(1)),
    bucket_counts_list      Array(UInt64) COMMENT 'bucket直方图计数值' CODEC (ZSTD(1)),
    negative_bucket_list    Nested ( left_value Decimal64(4),
        right_value Decimal64(4),
        count UInt64
        ) COMMENT '' CODEC (ZSTD(1)),
    zero_count              UInt64 COMMENT '' CODEC (ZSTD(1)),
    positive_bucket_list    Nested ( left_value Decimal64(4),
        right_value Decimal64(4),
        count UInt64
        ) COMMENT '' CODEC (ZSTD(1)),
    quantile_list           Nested ( quantile Decimal64(4),
        value Decimal64(4)
        ) COMMENT '' CODEC (ZSTD(1))
) ENGINE = MergeTree()
    PARTITION BY toYYYYMM(time_unix_nano)
    ORDER BY (enterprise_id, namespace, application_name, instance_id, metric_name, time_unix_nano)
    TTL toDateTime(time_unix_nano) + INTERVAL 20 YEAR DELETE;