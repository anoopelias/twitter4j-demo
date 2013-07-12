package twitter4j.demo;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.DirectMessage;
import twitter4j.demo.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PrivateMessages extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Some text3");
                
        try {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
            StringBuffer responseString = new StringBuffer("[");
            for (DirectMessage dm : twitter.getDirectMessages()) {
                responseString.append("{\"sender\" : \"").append(dm.getSenderId()).append("\", ");
                responseString.append("\"recipient\" : \"").append(dm.getRecipientId()).append("\", ");
                String text = dm.getText(); 
                System.out.println("Before >" + text);
                if(text.contains("\n")) {
                    System.out.println("After >" + Util.textToJson(text));
                }
                responseString.append("\"text\" : \"").append(Util.textToJson(dm.getText())).append("\"},");
            }
            responseString.deleteCharAt(responseString.length() - 1);
            responseString.append("]");

            System.out.println(responseString);

            response.getOutputStream().write(responseString.toString().getBytes());

        } catch (IOException e) {
           e.printStackTrace();
        } catch (TwitterException e) {
           e.printStackTrace();
        }

    }

}

