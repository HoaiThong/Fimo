package com.konkua.fimo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    private StringBuilder sb;

    public HttpHandler() {

    }

    public String getRespose(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL myUrl = new URL(url);
            urlConnection = (HttpURLConnection) myUrl.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }

    public String readStream1(InputStream in) throws IOException {
        String result = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"), 8);
        sb = new StringBuilder();
        String line = "0";
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        in.close();
        result = sb.toString();
        return result;
    }

    public String readStream(InputStream in) throws IOException {
//        InputStream in = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

}
