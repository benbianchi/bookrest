package bookrest;

import aerospike.AerospikeDao;
import bookrest.controller.readController;
import bookrest.resource.ReadResource;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.policy.WritePolicy;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class BookRestService extends Application<BookRestConfiguration> {

    public static void main(String[] args) throws Exception {
        new BookRestService().run(args);
    }

    public void run(BookRestConfiguration bookRestConfiguration, Environment environment) throws Exception {

        IAerospikeClient aerospikeClient = new AerospikeClient(
                bookRestConfiguration.getAerospikeConfiguration().getHost(),
                bookRestConfiguration.getAerospikeConfiguration().getPort()
        );

        WritePolicy policy = new WritePolicy();

        AerospikeDao aerospikeDao = new AerospikeDao(
                aerospikeClient,
                policy,
                bookRestConfiguration.getAerospikeConfiguration()
        );

        ReadResource readResource = new ReadResource(
                new readController(aerospikeDao)
        );

        environment.jersey().register(readResource);

    }
}
