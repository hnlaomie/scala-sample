package io.github.hnlaomie.config

import scala.sys.process._
import akka.japi.Util.immutableSeq
import com.typesafe.config.ConfigFactory

/**
  * spark相关配置参数
  *
  * @author <a href="mailto:laomiedev@163.com">laomie</a>
  **/
class SparkSettings {
    protected val config = ConfigFactory.load.getConfig("sample")

    /** Attempts to detect System property, falls back to config. */
    // Detect Spark Master and Cassandra entry point using dsetool
    val SparkMaster: String = try { "dsetool sparkmaster".!!.trim }
    catch { case x:Exception => sys.props.get("spark.master").getOrElse(config.getString("spark.master")) }

    val StreamingBatchInterval = config.getInt("spark.streaming.batch.interval")

    val StreamingConcurrentJobs = sys.props.get("spark.streaming.concurrentJobs")
        .getOrElse(config.getBytes("spark.streaming.concurrentJobs"))

    /** Attempts to detect System property, falls back to config,
      * to produce a comma-separated string of hosts. */
    val CassandraSeedNodes: String = sys.props.get("spark.cassandra.connection.host") getOrElse
        immutableSeq(config.getStringList("spark.cassandra.connection.host")).mkString(",")

    val KafkaBrokers: String = sys.props.get("spark.kafka.brokers") getOrElse
        immutableSeq(config.getStringList("spark.kafka.brokers")).mkString(",")

    // 相关主题
    val ShowTopics: String = sys.props.get("spark.kafka.topics") getOrElse
        immutableSeq(config.getStringList("spark.sample.show.topics")).mkString(",")
    val ClickTopics: String = sys.props.get("spark.kafka.topics") getOrElse
        immutableSeq(config.getStringList("spark.sample.click.topics")).mkString(",")

}
