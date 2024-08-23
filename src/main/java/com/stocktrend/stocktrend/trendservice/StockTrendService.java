package com.stocktrend.stocktrend.trendservice;

import com.stocktrend.stocktrend.trendentity.StockTrendEntity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StockTrendService {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private final AtomicReference<List<StockTrendEntity>> latestStockInfoList = new AtomicReference<>(new ArrayList<>());

    public List<StockTrendEntity> getLatest() {
        List<StockTrendEntity> entities = new ArrayList<>();
        try {
            String url = "https://www.google.com/finance/markets/gainers?hl=en&gl=in";
            String response = getStockTrend(url);
            Document doc = Jsoup.parse(response);

            Elements rows = doc.select(".Vd323d ul li");

            for (Element row : rows) {
                String stockText = row.text();

                String stockName = stockText;
                String currentPrice = "N/A";
                String changeAmount = "N/A";
                String percentageChange = "N/A";
                String trend = "N/A";

                StockTrendEntity entity = new StockTrendEntity(stockName, currentPrice, changeAmount, percentageChange, trend);
                entities.add(entity);
            }

            latestStockInfoList.set(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return latestStockInfoList.get();
    }

    public List<StockTrendEntity> getLatestStockInfo() {
        return latestStockInfoList.get();
    }

    private String getStockTrend(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}