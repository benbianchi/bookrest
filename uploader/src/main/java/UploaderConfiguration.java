import aerospike.AerospikeConfiguration;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Configuration used for the Uploader app
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class UploaderConfiguration {

    @NotEmpty
    private AerospikeConfiguration aerospikeConfiguration;

    public AerospikeConfiguration getAerospikeConfiguration() {
        return aerospikeConfiguration;
    }
}
