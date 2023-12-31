package com.dice.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

public class CsvDataProviders {

    @DataProvider(name = "csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> list = new ArrayList<Object[]>();
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "dataproviders" + File.separator + method.getDeclaringClass().getSimpleName() + "_"
                + method.getName() + ".csv";
        File file = new File(pathname);
        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[] { testData });
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());
        } catch (IOException e) {
            throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }

        return list.iterator();
    }

}
