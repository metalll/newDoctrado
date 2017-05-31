package Servlets;


import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by NSD on 24.05.17.
 */

@WebServlet(name = "File",urlPatterns = "/API/File")
public class FileControllerServlet extends HttpServlet{



    @Override // putFile
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URLConnection connection = new URL("https://datadoctrado-sviasy.rhcloud.com/file").openConnection(); //todo Data Doctrado

        connection.setDoOutput(true); // POST
// Copy headers if necessary.

        InputStream input1 = req.getInputStream();

        OutputStream output1 = connection.getOutputStream();
// Copy request body from input1 to output1.

        InputStream input2 = connection.getInputStream();
        OutputStream output2 = resp.getOutputStream();


// Copy response body from input2 to output2=

//        output1.write(input1.read());
//        output2.write(input2.read());

        connection.connect();

    }


}
