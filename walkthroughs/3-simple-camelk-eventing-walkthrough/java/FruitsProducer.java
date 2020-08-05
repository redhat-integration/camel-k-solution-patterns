// camel-k: language=java, dependency=camel-http
import org.apache.camel.builder.RouteBuilder;

public class FruitsProducer extends RouteBuilder {
    @Override
    public void configure() throws Exception {
            // Write your routes here, for example:
            from("kafka:producer?brokers=my-cluster-kafka-bootstrap:9092")
                .setHeader("CamelHttpMethod", constant("GET"))
                .toD("http:fruityvice.com/api/fruit/${body}?bridgeEndpoint=true")
                .split().jsonpath("$.[*]")
                .marshal().json()
                .log("${body}")
                .to("kafka:fruits?brokers=my-cluster-kafka-bootstrap:9092");
    }
}
