package com.example.rgr;

import androidx.annotation.Nullable;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;


public class NullBooleanAdapter  {
    @ToJson
    public Boolean toJson(@Nullable Boolean value) {
        return value != null ? value : true;
    }

    @FromJson
    public boolean fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.nextNull();
            return false;
        }
        return reader.nextBoolean();
    }
}
