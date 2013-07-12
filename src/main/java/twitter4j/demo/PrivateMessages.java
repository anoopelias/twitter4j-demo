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
                
        StringBuffer responseString = new StringBuffer();
        try {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
            if(twitter != null) {
                responseString.append("[");
                for (DirectMessage dm : twitter.getDirectMessages()) {
                    responseString.append("{\"sender\" : \"").append(dm.getSenderId()).append("\", ");
                    responseString.append("\"recipient\" : \"").append(dm.getRecipientId()).append("\", ");
                    responseString.append("\"text\" : \"").append(Util.textToJson(dm.getText())).append("\"},");
                }
                responseString.deleteCharAt(responseString.length() - 1);
                responseString.append("]");

                responseString.insert(0, "{ \"status\" : \"success\", " +
                    "\"response\" : ");
                responseString.append("}");

            }
            else {
                responseString.append("{ \"status\" : \"failure\", " +
                    "\"response\" : ");
                responseString.append("\"Probably you should login first\"}");
            }

            response.getOutputStream().write(responseString.toString().getBytes());
        } catch (IOException e) {
           e.printStackTrace();
        } catch (TwitterException e) {
           e.printStackTrace();
        }

    }

}

