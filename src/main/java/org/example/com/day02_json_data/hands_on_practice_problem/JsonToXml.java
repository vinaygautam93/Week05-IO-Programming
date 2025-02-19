package org.example.com.day02_json_data.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;

public class JsonToXml {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(new File("user.json"));  // ✅ JsonNode is now recognized

            // Convert JSON to XML
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

            // Print XML output
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
