package com.stocktrend.stocktrend.trendcontroller;

import com.stocktrend.stocktrend.trendentity.StockActualEntity;
import com.stocktrend.stocktrend.trendentity.StockTrendEntity;
import com.stocktrend.stocktrend.trendservice.StockActualTrendService;
import com.stocktrend.stocktrend.trendservice.StockLoser;
import com.stocktrend.stocktrend.trendservice.StockTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockTrendController {

    @Autowired
    private StockTrendService service;

    @Autowired
    private StockActualTrendService stockActualTrendService;

    @Autowired
    private StockLoser stockLoser;

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test endpoint is working!";
    }

    @GetMapping("/top-gainers-stocks")
    public List<StockTrendEntity> findAll() {
        List<StockTrendEntity> stockList = service.getLatest();  // This method should return List<Entity>
        if (stockList != null && !stockList.isEmpty()) {
            return stockList;
        } else {
            return List.of(new StockTrendEntity("No Data","No Data","No Data","No Data","No Data"));
        }
    }

    @GetMapping("/trending-stocks")
    public List<StockActualEntity> findall() {
        List<StockActualEntity> stockList = stockActualTrendService.getLatest();  // This method should return List<Entity>
        if (stockList != null && !stockList.isEmpty()) {
            return stockList;
        } else {
            return List.of(new StockActualEntity("No Data","No Data","No Data","No Data","No Data","No data","No Data"));
        }
    }

    @GetMapping("/loser-stocks")
    public List<StockActualEntity> find() {
        List<StockActualEntity> stockList = stockLoser.getLatest();  // This method should return List<Entity>
        if (stockList != null && !stockList.isEmpty()) {
            return stockList;
        } else {
            return List.of(new StockActualEntity("No Data","No Data","No Data","No Data","No Data","No data","No Data"));
        }
    }

}
