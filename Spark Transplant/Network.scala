
import org.apache.spark._
import org.apache.spark.rdd.RDD
// import classes required for using GraphX
import org.apache.spark.graphx._

// Load my user data and parse into tuples of user id and attribute list


object Network{
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    val dir="hdfs://ec2-54-213-1-78.us-west-2.compute.amazonaws.com:9000/"
    // Parse the edge data which is already in userId -> userId format
    val users:RDD[(VertexId,Double)]= sc.textFile(dir+"userName.nodes")
                                        .map(line =>((line.toLong,0)))

    val relation:RDD[Edge[Double]]=sc.textFile(dir+"relation.edges")
                                      .map(line=>line.split(" "))
                                      .map(ele=>Edge(ele(0).toLong,ele(1).toLong,0))

    val proNetwork = Graph(users,relation)


  }

}