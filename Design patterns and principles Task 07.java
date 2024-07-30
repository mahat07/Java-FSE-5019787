public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName) {
        this.stockName = stockName;
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}
public interface Observer {
    void update(String stockName, double stockPrice);
}
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("MobileApp " + appName + " received update: " + stockName + " is now $" + stockPrice);
    }
}
public class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double stockPrice) {
        System.out.println("WebApp " + appName + " received update: " + stockName + " is now $" + stockPrice);
    }
}

public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket appleStock = new StockMarket("Apple");

        Observer mobileApp1 = new MobileApp("App1");
        Observer webApp1 = new WebApp("Web1");

        appleStock.registerObserver(mobileApp1);
        appleStock.registerObserver(webApp1);

        appleStock.setStockPrice(150.00);
        System.out.println();

        appleStock.setStockPrice(155.00);
        System.out.println();

        appleStock.deregisterObserver(mobileApp1);

        appleStock.setStockPrice(160.00);
    }
}
