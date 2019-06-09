import org.apache.commons.cli.Options;

public class UploaderOptions {

    static final String ASSET_FILE_TO_LOAD = "Asset File to load";
    static final String CONFIG_FILE_OPTION_LONG_NAME = "config";
    static final String CONFIGURATION_FILE_TO_LOAD_DESCRIPTION = "Configuration File to load";
    static final String CONFIG_FILE_OPTION = "c";
    static final String LOAD_FROM_FILE_OPTION = "f";
    static final String LOAD_FROM_FILE_OPTION_LONG_NAME = "file";

    static Options getOptions() {
        return new Options()
                .addRequiredOption(
                        LOAD_FROM_FILE_OPTION,
                        LOAD_FROM_FILE_OPTION_LONG_NAME,
                        true,
                        ASSET_FILE_TO_LOAD
                )
                .addRequiredOption(
                        CONFIG_FILE_OPTION,
                        CONFIG_FILE_OPTION_LONG_NAME,
                        true,
                        CONFIGURATION_FILE_TO_LOAD_DESCRIPTION
                );

    }
}
