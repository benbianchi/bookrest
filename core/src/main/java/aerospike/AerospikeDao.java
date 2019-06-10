package aerospike;

import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.ResultCode;
import com.aerospike.client.policy.WritePolicy;

/**
 * This class is used to write and read lines of text from an aerospike cluster.
 */
public class AerospikeDao {

    /**
     * The bin's field name we store our text in.
     */
    private static final String BIN_TEXT_NAME = "text";

    /**
     * The aerospike client we are using to send to an aerospike cluster.
     */
    private final IAerospikeClient client;

    /**
     * The configuration we will use to connect to aerspike.
     */
    private final AerospikeConfiguration aerospikeConfiguration;

    /**
     * Policy to use for writing to aerospike. We can use this to make sure
     * the uploader can only write, and the rest app can only read.
     */
    private WritePolicy policy;

    /**
     * A Dao that can communicate with Aerospike.
     *
     * @param client                 the client we will use to write to aerospike
     * @param policy                 A policy we use when accessing Aerospike
     * @param aerospikeConfiguration the configuration we are using to write/read from AS.
     */
    public AerospikeDao(IAerospikeClient client, WritePolicy policy, AerospikeConfiguration aerospikeConfiguration) {
        this.client = client;
        this.policy = policy;
        this.aerospikeConfiguration = aerospikeConfiguration;
    }

    /**
     * Save a line from a book and its text to aerospike.
     *
     * @param lineNumber the line number we are saving
     * @param text       a {@link String} we want to save at that line number
     * @return whether or not we saved
     * @throws AerospikeException When something goes wrong with the save.
     */
    public boolean writeLine(int lineNumber, String text) throws AerospikeException {
        try {
            this.client.put(
                    policy,
                    generateKeyFromLineNumber(lineNumber),
                    new Bin(BIN_TEXT_NAME, text)
            );

            return true;
        } catch (AerospikeException ex) {
            throw ex;
        }
    }

    /**
     * Get a line from aerospike
     * @param lineNumber the line number to search for
     * @return the line in string form
     * @throws AerospikeException when something goes wrong on the aerospike side.
     */
    public String getLine(int lineNumber) throws AerospikeException {
        try {
            Record r = this.client.get(policy, generateKeyFromLineNumber(lineNumber));

            // If I had more time, I would persist the information about the asset
            // In another table.
            if (r == null) {
                throw new AerospikeException(ResultCode.KEY_NOT_FOUND_ERROR);
            }

            return r.getString(BIN_TEXT_NAME);
        } catch (AerospikeException ex) {
            throw ex;
        }

    }


    /**
     * Get a key, given a line number.
     *
     * @param lineNumber a non null {@link Integer}
     * @return a key that we can use to access aerospike
     */
    private Key generateKeyFromLineNumber(int lineNumber) {
        return new Key(
                aerospikeConfiguration.getNamespace(),
                aerospikeConfiguration.getSetName(),
                String.format("asset-%s", String.valueOf(lineNumber))
        );
    }
}
