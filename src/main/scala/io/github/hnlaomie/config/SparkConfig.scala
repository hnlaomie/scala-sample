package io.github.hnlaomie.config

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}

/**
  * spark相关设置
  *
  * @author <a href="mailto:laomiedev@163.com">laomie</a>
  */
class SparkConfig extends SparkSettings {
    val settings = new SparkSettings
    import settings._

    val conf = new SparkConf(true)
        .setMaster(SparkMaster)
        .setAppName(getClass.getSimpleName)
        .set("spark.cassandra.connection.host", CassandraSeedNodes)
        .set("spark.streaming.concurrentJobs", StreamingConcurrentJobs.toString)
        .set("spark.shuffle.io.preferDirectBufs", "false")
        //.set("mapreduce.job.user.classpath.first", "true")
        //.set("spark.driver.userClassPathFirst", "true")
        //.set("spark.driver.allowMultipleContexts", "true")

    val sc = new SparkContext(conf)
}
