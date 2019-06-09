package bookrest;

import aerospike.AerospikeConfiguration;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class BookRestConfiguration extends Configuration {


    @NotNull
    private AerospikeConfiguration aerospikeConfiguration;

    public AerospikeConfiguration getAerospikeConfiguration() {
        return aerospikeConfiguration;
    }
}
