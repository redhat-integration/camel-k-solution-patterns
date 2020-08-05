// camel-k: language=java
import org.apache.camel.builder.RouteBuilder;

public class PutToTopic extends RouteBuilder {
  @Override
  public void configure() throws Exception {
      // try things like all, Banana, Orange, Tomato
      from("timer://foo?repeatCount=1")
        .setBody()
          .simple("all")
        .to("kafka:producer?brokers=my-cluster-kafka-bootstrap:9092");
  }
}
