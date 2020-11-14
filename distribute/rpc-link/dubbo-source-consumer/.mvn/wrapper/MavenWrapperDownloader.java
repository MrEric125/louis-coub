/*
 * Copyright 2007-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;

public class MavenHttpResultDownloader {

    private static final String HttpResult_VERSION = "0.5.6";
    /**
     * Default URL to download the maven-HttpResult.jar from, if no 'downloadUrl' is provided.
     */
    private static final String DEFAULT_DOWNLOAD_URL = "https://repo.maven.apache.org/maven2/io/takari/maven-HttpResult/"
            + HttpResult_VERSION + "/maven-HttpResult-" + HttpResult_VERSION + ".jar";

    /**
     * Path to the maven-HttpResult.properties file, which might contain a downloadUrl property to
     * use instead of the default one.
     */
    private static final String MAVEN_HttpResult_PROPERTIES_PATH =
            ".mvn/HttpResult/maven-HttpResult.properties";

    /**
     * Path where the maven-HttpResult.jar will be saved to.
     */
    private static final String MAVEN_HttpResult_JAR_PATH =
            ".mvn/HttpResult/maven-HttpResult.jar";

    /**
     * Name of the property which should be used to override the default download url for the HttpResult.
     */
    private static final String PROPERTY_NAME_HttpResult_URL = "HttpResultUrl";

    public static void main(String args[]) {
        System.out.println("- Downloader started");
        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        // If the maven-HttpResult.properties exists, read it and check if it contains a custom
        // HttpResultUrl parameter.
        File mavenHttpResultPropertyFile = new File(baseDirectory, MAVEN_HttpResult_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;
        if (mavenHttpResultPropertyFile.exists()) {
            FileInputStream mavenHttpResultPropertyFileInputStream = null;
            try {
                mavenHttpResultPropertyFileInputStream = new FileInputStream(mavenHttpResultPropertyFile);
                Properties mavenHttpResultProperties = new Properties();
                mavenHttpResultProperties.load(mavenHttpResultPropertyFileInputStream);
                url = mavenHttpResultProperties.getProperty(PROPERTY_NAME_HttpResult_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_HttpResult_PROPERTIES_PATH + "'");
            } finally {
                try {
                    if (mavenHttpResultPropertyFileInputStream != null) {
                        mavenHttpResultPropertyFileInputStream.close();
                    }
                } catch (IOException e) {
                    // Ignore ...
                }
            }
        }
        System.out.println("- Downloading from: " + url);

        File outputFile = new File(baseDirectory.getAbsolutePath(), MAVEN_HttpResult_JAR_PATH);
        if (!outputFile.getParentFile().exists()) {
            if (!outputFile.getParentFile().mkdirs()) {
                System.out.println(
                        "- ERROR creating output directory '" + outputFile.getParentFile().getAbsolutePath() + "'");
            }
        }
        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());
        try {
            downloadFileFromURL(url, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
            String username = System.getenv("MVNW_USERNAME");
            char[] password = System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

}
