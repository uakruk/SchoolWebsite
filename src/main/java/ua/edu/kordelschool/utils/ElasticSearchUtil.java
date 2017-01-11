package ua.edu.kordelschool.utils;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ElasticSearchUtil {

    public static void indexEvent(String type, String message) {
        TransportAddress address = null;
        try {
            address = new InetSocketTransportAddress(InetAddress.getByName("elasticsearch"), 9300);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Client client = new PreBuiltTransportClient(Settings.EMPTY).
                addTransportAddress(address);
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("postDate", new Date());
        json.put("message", message);
        IndexResponse response = client.prepareIndex("events-", type)
                .setSource(json)
                .get();
        client.close();
    }
}
