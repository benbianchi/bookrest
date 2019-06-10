package bookrest;

import aerospike.AerospikeDao;
import bookrest.controller.ReadController;
import bookrest.resource.ReadResource;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.policy.WritePolicy;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class BookRestService extends Application<BookRestConfiguration> {

    /**
     * Main entry for the application
     * @param args an array of {@link String} objects describing how to launch the servce
     * @throws Exception If something happens that causes us to kill the app
     */
    public static void main(String[] args) throws Exception {
        new BookRestService().run(args);
    }

    /**
     * Run the application
     */
    public void run(BookRestConfiguration bookRestConfiguration, Environment environment) throws Exception {

        IAerospikeClient aerospikeClient = null;

        try {
        aerospikeClient = new AerospikeClient(
                bookRestConfiguration.getAerospikeConfiguration().getHost(),
                bookRestConfiguration.getAerospikeConfiguration().getPort()
        );
        } catch (AerospikeException ex) {
            System.err.println("Couldnt connect to Aerospike on "+
            bookRestConfiguration.getAerospikeConfiguration().getHost() +":"+
            bookRestConfiguration.getAerospikeConfiguration().getPort().toString()
            );

            System.exit(-1);
        }

        // Setup out access to AS
        WritePolicy policy = new WritePolicy();
        AerospikeDao aerospikeDao = new AerospikeDao(
                aerospikeClient,
                policy,
                bookRestConfiguration.getAerospikeConfiguration()
        );

        // Hook up our resources
        ReadResource readResource = new ReadResource(
                new ReadController(aerospikeDao)
        );

        environment.jersey().register(readResource);

    }
}
