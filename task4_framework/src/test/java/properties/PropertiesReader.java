package properties;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final static String ENVIRONMENT = "test";
    private final static String PROPERTY_FILE = "src/main/resources/" + ENVIRONMENT + "-configuration.properties";
    private static Properties properties;
    private static BufferedReader bufferedReader;

    public static Properties configurationFileReader() {
        try {
            bufferedReader = new BufferedReader(new FileReader(PROPERTY_FILE));
            properties = new Properties();
            try {
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File configuration.properties not found: " + PROPERTY_FILE);
        }
        return properties;
    }


    public static String getPageUrl() {
        return configurationFileReader().getProperty("url");
    }

    public static String getTypeOfBrowser() {
        return configurationFileReader().getProperty("browser");
    }

    public static String getProductName() {
        return configurationFileReader().getProperty("productName");
    }

    public static String getNumberOfInstances() {
        return configurationFileReader().getProperty("numberOfInstances");
    }

    public static String getInstancesReasonInput() {
        return configurationFileReader().getProperty("instancesReason");
    }

    public static String getProvisioningModel() {
        return configurationFileReader().getProperty("provisioningModel");
    }

    public static String getMachineFamily() {
        return configurationFileReader().getProperty("machineFamily");
    }

    public static String getSeries() {
        return configurationFileReader().getProperty("series");
    }

    public static String getMachineType() {
        return configurationFileReader().getProperty("machineType");
    }

    public static String getGpuType() {
        return configurationFileReader().getProperty("gpuType");
    }

    public static String getNumberOfCpu() {
        return configurationFileReader().getProperty("numberOfCpu");
    }

    public static String getLocalSsd() {
        return configurationFileReader().getProperty("localSsd");
    }

    public static String getDataCenterLocation() {
        return configurationFileReader().getProperty("dataCenterLocation");
    }

    public static String getCommittedUsage() {
        return configurationFileReader().getProperty("committedUsage");
    }

    public static String getFinalPrice() {
        return configurationFileReader().getProperty("finalPrice");
    }
}