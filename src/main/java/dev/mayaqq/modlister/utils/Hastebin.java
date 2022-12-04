package dev.mayaqq.modlister.utils;

import okhttp3.*;
import java.io.IOException;

public class Hastebin {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public String post(String text) throws IOException {
        String url = "https://deftb.in/documents";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(text, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return "https://deftb.in/" + response.body().string().replaceAll("\"", "").replaceAll("key:", "").replaceAll("\\{", "").replaceAll("}", "");
        }
    }
}
