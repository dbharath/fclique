package net.fclique.crowler.service;

/**
 * Created by devesh.bharathan
 */
public class ServiceFactory {

    private static LoggingService loggingService;
    private static FlipkartScrapingService flipkartScrapingService;
    private static AmazonScrapingService amazonScrapingService;
    private static SnapdealScrapingService snapdealScrapingService;
    private static TradusScrapingService tradusScrapingService;
    public static InfibeamScrapingService infibeamScrapingService;
    public static EbayScrapingService ebayScrapingService;
    public static NaaptolScrapingService naaptolScrapingService;
    public static HomeShop18ScrapingService homeShop18ScrapingService;
    public static ShoppingIndiaTimesScrapingService shoppingIndiaTimesScrapingService;

    public static LoggingService getLoggingService() {
        if (loggingService == null)
            loggingService = new LoggingService();
        return loggingService;
    }

    public static FlipkartScrapingService getFlipkartScrapingService() {
        if (flipkartScrapingService == null)
            flipkartScrapingService = new FlipkartScrapingService();
        return flipkartScrapingService;
    }

    public static AmazonScrapingService getAmazonScrapingService() {
        if (amazonScrapingService == null)
            amazonScrapingService = new AmazonScrapingService();
        return amazonScrapingService;
    }

    public static SnapdealScrapingService getSnapdealScrapingService() {
        if (snapdealScrapingService == null)
            snapdealScrapingService = new SnapdealScrapingService();
        return snapdealScrapingService;
    }

    public static TradusScrapingService getTradusScrapingService() {
        if (tradusScrapingService == null)
            tradusScrapingService = new TradusScrapingService();
        return tradusScrapingService;
    }

    public static InfibeamScrapingService getInfibeamScrapingService() {
        if (infibeamScrapingService == null)
            infibeamScrapingService = new InfibeamScrapingService();
        return infibeamScrapingService;
    }

    public static EbayScrapingService getEbayScrapingService() {
        if (ebayScrapingService == null)
            ebayScrapingService = new EbayScrapingService();
        return ebayScrapingService;
    }

    public static NaaptolScrapingService getNaaptolScrapingService() {
        if (naaptolScrapingService == null)
            naaptolScrapingService = new NaaptolScrapingService();
        return naaptolScrapingService;
    }

    public static HomeShop18ScrapingService getHomeShop18ScrapingService() {
        if (homeShop18ScrapingService == null)
            homeShop18ScrapingService = new HomeShop18ScrapingService();
        return homeShop18ScrapingService;
    }
    
    public static ShoppingIndiaTimesScrapingService getShoppingIndiaTimesScrapingService() {
        if (shoppingIndiaTimesScrapingService == null)
        	shoppingIndiaTimesScrapingService = new ShoppingIndiaTimesScrapingService();
        return shoppingIndiaTimesScrapingService;
    }
}
