package org.sch.issecurity.iam.tools.ADSync.utils;

/**
 * Created by XiChen on 2/20/2017.
 */

import org.sch.issecurity.iam.tools.ADSync.ADSync;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.sql.Timestamp;

public class ADSyncUtils {
    @SuppressWarnings("unchecked")
    public static Map<String, String> loadPropertiesFile(String propertiesFileName) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(propertiesFileName));
        return (Map) properties;
    }

    public static void printClassPathResource(String resourceName) {
        try {
            InputStream stream = ADSync.class.getResourceAsStream(resourceName);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            String line = in.readLine();
            while (line != null) {
                System.out.println(line);
                line = in.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Timestamp converADdateToTimestamp(Long ADdateLong) throws ParseException {

        Timestamp theTimestamp = null;

        if (ADdateLong != null) {
            long ADdate = ADdateLong.longValue();

            // Filetime Epoch is 01 January, 1601
            // java date Epoch is 01 January, 1970
            // so take the number and subtract java Epoch:
            long javaTime = ADdate - 0x19db1ded53e8000L;

            // convert UNITS from (100 nano-seconds) to (milliseconds)
            javaTime /= 10000;

            // Date(long date)
            // Allocates a Date object and initializes it to represent
            // the specified number of milliseconds since the standard base
            // time known as "the epoch", namely January 1, 1970, 00:00:00 GMT.
            Date theDate = new Date(javaTime);

            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Date maxDate = sdf.parse("2199-12-31");
            long maxDatelong = 162834048000000000L;
            if (ADdate < maxDatelong) {
                theTimestamp = new Timestamp(theDate.getTime());

            }
        }
        return theTimestamp;
    }

}
