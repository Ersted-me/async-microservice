package ru.ersted.asyncmicroservice.model.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class URL {
    private String protocol;
    private String host;
    private String path;
    private String query;

    @Override
    public String toString() {
        //https://www.example.com/path/to/site?query
        StringBuilder sb = new StringBuilder();
        sb
                .append(protocol)
                .append("://")
                .append(host)
                .append("/")
                .append(path);

    if(query != null) {
        sb.append("?").append(query);
    }
        return sb.toString();
    }
}