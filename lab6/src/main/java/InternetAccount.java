public interface InternetAccount extends Subscriber {
    void buyTrafficPackage(int mb, double price);
    void setSpeedLimit(int mbps);
    int getSpeedLimit();
}