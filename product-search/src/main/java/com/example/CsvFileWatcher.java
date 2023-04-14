package com.example;

import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collections;

public class CsvFileWatcher extends Thread {
    private final String csvDirectoryPath;
    private final int checkIntervalMillis;

    public CsvFileWatcher(String csvDirectoryPath, int checkIntervalMillis) {
        this.csvDirectoryPath = csvDirectoryPath;
        this.checkIntervalMillis = checkIntervalMillis;
    }

    @Override
    public void run() {
        try {
            // Create a watch service for the CSV directory
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path csvDirectory = Paths.get(csvDirectoryPath);
            csvDirectory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            System.out.println("thread started successfully ....... !");
            // Continuously check for new files in the directory
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    // Get the filename of the new file
                    String filename = event.context().toString();

                    // Parse the new CSV file and add its T-shirts to the data structure
                    File file = new File(csvDirectoryPath, filename);
                    if (file.isFile() && file.getName().endsWith(".csv")) {
                        System.out.println("New CSV file added: " + filename );
                        System.out.println("Enter to continue ...... ");
                        String finalFilename = "src/main/java/com/resource/" + filename;
                        ProductSearch productSearch = new ProductSearch(Collections.singletonList(finalFilename));
                        productSearch.loadProducts();
                    }
                }
                key.reset();
                // Sleep the thread for the check interval before checking for new files again
                Thread.sleep(checkIntervalMillis);
            }
        } catch (InterruptedException e) {
            System.err.println("CsvFileWatcher thread interrupted");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error setting up watch service for CSV directory: " + csvDirectoryPath);
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
