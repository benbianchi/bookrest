package bookrest;

import aerospike.AerospikeConfiguration;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Configuration used for the rest service.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRestConfiguration extends Configuration {

    /**
     * The aerospike configuration use for the rest service
     */
    @NotNull
    private AerospikeConfiguration aerospikeConfiguration;

    public AerospikeConfiguration getAerospikeConfiguration() {
        return aerospikeConfiguration;
    }
}
