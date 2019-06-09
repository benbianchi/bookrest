package aerospike;

import javax.validation.constraints.NotEmpty;

/**
 * A configuration we can use for connecting and using aerospike as a resource
 */
public class AerospikeConfiguration {

    /**
     * The hostname of the AS cluster
     */
    @NotEmpty
    private String host;

    /**
     * The port we use to connect
     */
    @NotEmpty
    private Integer port;

    /**
     * The namespace we are using
     */
    @NotEmpty
    private String namespace;

    /**
     * The setname we are using;
     */
    @NotEmpty
    private String setName;

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getSetName() {
        return setName;
    }
}
