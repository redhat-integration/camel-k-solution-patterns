// camel-k: language=java, dependency=camel-http
import org.apache.camel.builder.RouteBuilder;

public class FruitsProducer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
            from("kafka:producer?brokers=rhmi-cluster-kafka-bootstrap.redhat-rhmi-amq-streams:9092")
                .setHeader("CamelHttpMethod", constant("GET"))
                .toD("http:fruityvice.com/api/fruit/${body}?bridgeEndpoint=true")
                .split().jsonpath("$.[*]")
                .marshal().json()
                .log("${body}")
                .to("kafka:fruits?brokers=rhmi-cluster-kafka-bootstrap.redhat-rhmi-amq-streams:9092");
    }
}
