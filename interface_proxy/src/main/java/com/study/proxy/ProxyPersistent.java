package com.study.proxy;

import com.study.config.ScanProperty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface ProxyPersistent {

    void write(Map<String, ScanProperty> map);

    Map<String, ScanProperty> read();

}
