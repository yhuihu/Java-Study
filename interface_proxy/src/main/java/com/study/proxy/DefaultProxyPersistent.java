package com.study.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.study.config.ScanProperty;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DefaultProxyPersistent implements ProxyPersistent {
    @Override
    public void write(Map<String, ScanProperty> map) {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + File.separator + "proxyMethod.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (PrintWriter printWriter = new PrintWriter(file)) {
            String jsonString = JSON.toJSONString(map);
            printWriter.write(jsonString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, ScanProperty> read() {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + File.separator + "proxyMethod.json");
        try {
            if (!file.exists()){
                return null;
            }
            List<String> strings = Files.readAllLines(file.toPath());
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : strings) {
                stringBuilder.append(string);
            }
            ObjectMapper mapper = new ObjectMapper();
            Map<String, JSONObject> parse = (Map<String, JSONObject>) JSON.parseObject(stringBuilder.toString(), HashMap.class);
            Map<String,ScanProperty> map = new HashMap<>();
            parse.forEach((key,value)->{
                map.put(key,value.toJavaObject(ScanProperty.class));
            });
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
