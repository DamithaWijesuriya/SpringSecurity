package com.config.core.Servises;

import com.config.core.Interfaces.IConnector;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by hsenid on 5/9/17.
 */
public class ConnectHTTPClient implements IConnector {
    static final String PostUrl="https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20170503T091049Z.71525def9ab833ab.c8ab2a94ef606d3d378564c7e3e1536a56b45e40";

    @Override
    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage) throws IOException, ParseException {
        return null;
    }

    @Override
    public HashMap<String, String> getAllLanguagesList() throws Exception {


        CloseableHttpClient client = HttpClientBuilder.create().build();
        //send the request
        HttpPost request = new HttpPost(PostUrl);

        //excute the request to obtain the response
        CloseableHttpResponse response = client.execute(request);

        /** Get the response */
        InputStream input = response.getEntity().getContent();

        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        Document document=documentBuilder.parse(input);
        NodeList nodeList=document.getElementsByTagName("Item");


        HashMap<String,String> valuesList=new HashMap<String,String>();
        for(int i=0;i<nodeList.getLength();i++)
        {
            String key=nodeList.item(i).getAttributes().getNamedItem("Key").getNodeValue();
            String value=nodeList.item(i).getAttributes().getNamedItem("value").getNodeValue();
            valuesList.put(key,value);
        }

return valuesList;
    }


    public String translateText(String FromLanguage,String ToLanguage,String InputText) throws Exception
    {
       String OutPut;
        String translateURL="https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20170503T091049Z.71525def9ab833ab.c8ab2a94ef606d3d378564c7e3e1536a56b45e40";
        HttpClient httpClient=new DefaultHttpClient();
        HttpGet request=new HttpGet(translateURL);
        HttpResponse httpResponse=httpClient.execute(request);


        return ;
    }
}
