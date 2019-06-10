package bookrest.controller;

import com.aerospike.client.AerospikeException;

import aerospike.AerospikeDao;

/**
 * Read controller for AS, we can put business logic in here
 */
public class ReadController {

    /**
     * Aerospike dao we use to connect and get info
     */
    private final AerospikeDao aerospikeDao;

    /**
     * Constructor
     * 
     * @param aerospikeDao the dao we use to talk to AS.
     */
    public ReadController(AerospikeDao aerospikeDao) {
        this.aerospikeDao = aerospikeDao;
    }

    /**
     * Get a line of the asset from the DB
     * 
     * @param lineNumber the line number we want to look up
     * @return the line of text
     * @throws AerospikeException when something goes wrong with AS, or the line
     * doesn't exist.
     */
    public String getLine(int lineNumber) throws AerospikeException {
        return this.aerospikeDao.getLine(lineNumber);
    }
}
