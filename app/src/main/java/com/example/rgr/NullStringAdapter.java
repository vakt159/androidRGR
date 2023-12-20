package com.example.rgr;

import androidx.annotation.Nullable;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.ToJson;

import java.io.IOException;

public class NullStringAdapter {
    @ToJson
    public String toJson(@Nullable String value) {
        return value != null ? value : "";
    }

    @FromJson
    public String fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.nextNull();
            return "No information available";
        }
        return reader.nextString();
    }
}
