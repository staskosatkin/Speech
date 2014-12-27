package common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpeechDecoder {
    public static void request(String filename) {
        java.io.File file = new java.io.File(filename);
        
        String url = "https://www.google.com/speech-api/v2/recognize?output=json&client=chromium&maxresults=6&pfilter=2&lang=en-us&key=AIzaSyD2cSaW_EQWhF99QxrNhv2p0AE7V9yV3rw";
        
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(120000);
            connection.setRequestProperty("Content-Type", "audio/x-flac; rate=44100;");
            try {
                OutputStream cos = connection.getOutputStream();
                FileInputStream fis = new FileInputStream(file);
                try {
                    
                    int length;
                    byte[] buffer = new byte[4096];
                    while(-1 != (length = fis.read(buffer))) {
                        cos.write(buffer, 0, length);
                    }
                } finally {
                    fis.close();
                    cos.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            InputStream in = connection.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int len;
            byte[] buf = new byte[4096];
            while(-1 != (len = in.read(buf))) {
                bos.write(buf, 0, len);
            }
            in.close();

            String output = bos.toString("UTF-8");

            Console.writeLine(SpeechDecoder.processResponse(output));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static String processResponse(String input) {

        String jsStr = input.substring(input.indexOf("\n"));

        JsonParser parser = new JsonParser();

        JsonObject jsonObject = parser.parse(jsStr).getAsJsonObject();

        return jsonObject.getAsJsonArray("result")
                .get(0)
                .getAsJsonObject().getAsJsonArray("alternative")
                .get(0)
                .getAsJsonObject()
                .getAsJsonPrimitive("transcript")
                .toString();
    }
}
