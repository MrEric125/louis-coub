package protocol.http;

import framework.Invocation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invacation) {
        try {
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream ops = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(ops);
            oos.writeObject(invacation);
            oos.flush();
            oos.close();



//            InputStream inputStream = httpURLConnection.getInputStream();
//            String result = IOUtils.toString(inputStream);
//            return result;
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
