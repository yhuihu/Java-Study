package com.study;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;
import java.util.Properties;

/**
 * @author ${USER}
 * @version 1.0.0
 * @description flink demo
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("decimal.handling.mode", "double"); //debezium 小数转换处理策略
        properties.setProperty("database.serverTimezone", "GMT+8"); //debezium 配置以database. 开头的属性将被传递给jdbc url


        MySqlSource<String> mySqlSource =
                MySqlSource.<String>builder()
                        .hostname("192.168.0.103")
                        .port(3306)
                        .connectTimeout(Duration.ofSeconds(30))
                        .databaseList("mydb")
                        .tableList("mydb.my_config")
                        .username("root")
                        .password("123456")
                        .deserializer(new JsonDebeziumDeserializationSchema())
                        .startupOptions(StartupOptions.initial())
                        .includeSchemaChanges(true) // output the schema changes as well
                        .debeziumProperties(properties)
                        .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // set the source parallelism to 4
        DataStreamSource<String> mySqlParallelSource = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySqlParallelSource");
        mySqlParallelSource.print();
        env.execute("Print MySQL Snapshot + Binlog");

    }
}
