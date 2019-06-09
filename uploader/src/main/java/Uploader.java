import aerospike.AerospikeDao;
import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Uploader {

    /**
     * The Application Name
     */
    private static final String APPLICATION_NAME = "Book Rest Uploader";

    /**
     * Failure code for cmd failure
     */
    private static final int APPLICATION_FAILURE_CODE = -1;

    /**
     * String we show user if the file was not found.
     */
    private static final String FILE_WAS_NOT_FOUND = "File was not Found!";

    /**
     * Object Mapper we will use to deserialize a configuration
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * The configuration we use for uploading.
     */
    private static UploaderConfiguration configuration;

    /**
     * Main Entry for our uploader program.
     *
     * @param args arguments passed to the program via CLI
     */
    public static void main(String[] args) {

        // Setup parser
        Options uploaderOptions = UploaderOptions.getOptions();
        CommandLine cmd = null;
        CommandLineParser parser = new DefaultParser();

        // Attempt to read
        try {
            cmd = parser.parse(uploaderOptions, args);
        } catch (ParseException ex) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(APPLICATION_NAME, uploaderOptions);
            System.exit(APPLICATION_FAILURE_CODE);
        }

        System.out.println("Beginning to Read Configuration");

        try {
            configuration = objectMapper.readValue(new File(cmd.getOptionValue('c')), UploaderConfiguration.class);
        } catch (IOException ex) {
            System.out.println("Unable to load and parse configuration!");
            ex.printStackTrace();
            System.exit(APPLICATION_FAILURE_CODE);
        }


        System.out.println("Beginning Uploading Process.");
        File assetFile = new File(cmd.getOptionValue('f'));

        UploaderConfiguration configuration = null;

        IAerospikeClient aerospikeClient = new AerospikeClient(
                configuration.getAerospikeConfiguration().getHost(),
                configuration.getAerospikeConfiguration().getPort()
        );


        // Build an aerospike client and dao, We should not overwrite anything.
        RecordExistsAction recordExistsAction = RecordExistsAction.CREATE_ONLY;
        WritePolicy policy = new WritePolicy();
        policy.recordExistsAction = recordExistsAction;
        AerospikeDao aerospikeDao = new AerospikeDao(aerospikeClient, policy, configuration.getAerospikeConfiguration());


        try {
            Scanner sc = new Scanner(assetFile);
            int line = 0;
            while (sc.hasNextLine()) {

                try {
                    if (aerospikeDao.writeLine(line, sc.nextLine())) {
                        line++;
                    }
                } catch (AerospikeException e) {
                    System.err.println("Unable to write to aerospike!");
                    e.printStackTrace();
                    System.exit(APPLICATION_FAILURE_CODE);
                }
            }


        } catch (FileNotFoundException e) {
            System.out.println(FILE_WAS_NOT_FOUND);
            e.printStackTrace();
            System.exit(APPLICATION_FAILURE_CODE);
        }
    }

}
