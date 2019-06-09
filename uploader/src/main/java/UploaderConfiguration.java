import aerospike.AerospikeConfiguration;

import javax.validation.constraints.NotEmpty;

class UploaderConfiguration {

    @NotEmpty
    private AerospikeConfiguration aerospikeConfiguration;

    public AerospikeConfiguration getAerospikeConfiguration() {
        return aerospikeConfiguration;
    }
}
