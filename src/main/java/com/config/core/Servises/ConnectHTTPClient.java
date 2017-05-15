package com.config.core.Servises;

import com.config.core.Interfaces.IConnector;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Service
public class ConnectHTTPClient implements IConnector {

    PropertyFileReader properties = new PropertyFileReader();


    final String PostUrl = properties.getproperty("languagelist.url", "system.properties");


    //for testing
    public static void main(String[] args) throws Exception {
        /** for testing purpose of this class*/
        ConnectHTTPClient client = new ConnectHTTPClient();
        String ex2 = client.translate_text("en", "ru", "Hello");
        System.out.println(ex2);

        System.out.println("TRANS DONE");
    }


    public HashMap<String, String> getLangs() throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(PostUrl);


        CloseableHttpResponse response = client.execute(request);

        InputStream input = response.getEntity().getContent();


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        Document doc = builder.parse(input);

        NodeList nameNodesList = doc.getElementsByTagName("Item");

        HashMap<String, String> listValues = new HashMap<String, String>();

        for (int i = 0; i < nameNodesList.getLength(); i++) {

            String key = nameNodesList.item(i).getAttributes().getNamedItem("key").getNodeValue();
            String value = nameNodesList.item(i).getAttributes().getNamedItem("value").getNodeValue();
            listValues.put(key, value);

        }

        return listValues;

    }

    public String translate_text(String o_lan, String t_lan, String text_input) throws Exception {


        String output;

        String url = properties.getproperty("translate.url", "system.properties");

        /** URL sent to the API to get the string translated*/
        final String transUrl = url + o_lan + "-" + t_lan + "&text=" + text_input;

        /**send the request to the server thorough HttpClient*/
        org.apache.http.client.HttpClient httpClient_translate = new DefaultHttpClient();
        HttpGet request = new HttpGet(transUrl);
        org.apache.http.HttpResponse response2 = httpClient_translate.execute(request);


        /**Get the response*/
        InputStream input2 = response2.getEntity().getContent();

        /**creating DOM object*/
        DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder2 = dbf2.newDocumentBuilder();
        Document doc = builder2.parse(input2);

        NodeList text_tag = doc.getElementsByTagName("text");

        /** get the string value of the content in the text TAG*/
        output = String.valueOf(text_tag.item(0).getTextContent());

        return output;
    }


    @Override
    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) throws IOException, ParseException {
        return null;
    }

    @Override
    public HashMap<String, String> getAllLanguagesList() {
        return null;
    }
}