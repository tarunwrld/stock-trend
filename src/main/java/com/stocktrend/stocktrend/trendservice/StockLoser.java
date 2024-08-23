package com.stocktrend.stocktrend.trendservice;

import com.stocktrend.stocktrend.trendentity.StockActualEntity;
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
public class StockLoser {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private final AtomicReference<List<StockActualEntity>> latestStockInfoList = new AtomicReference<>(new ArrayList<>());

    public List<StockActualEntity> getLatest() {
        List<StockActualEntity> entities = new ArrayList<>();
        try {
            String url = "https://www.financialexpress.com/market/nse-top-losers/";
            String response = getStockTrend(url);
            Document doc = Jsoup.parse(response);

            // Assuming stock information is inside a table or similar structure
            Elements rows = doc.select(".full-width tr"); // Adjust selector based on actual HTML

            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.size() < 5) continue; // Ensure there are enough columns

                String stockName = cols.get(0).text();
                String currentPrice = cols.get(1).text();
                String changeAmount = cols.get(2).text();
                String percentageChange = cols.get(3).text();
                String prevclose = cols.get(4).text();
                String high = cols.get(5).text();
                String low = cols.get(6).text();

                // Create a StockTrendEntity with actual data
                StockActualEntity entity = new StockActualEntity(stockName, currentPrice, changeAmount, percentageChange, prevclose,high,low);
                entities.add(entity);
            }

            latestStockInfoList.set(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return latestStockInfoList.get();
    }

    public List<StockActualEntity> getLatestStockInfo() {
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

