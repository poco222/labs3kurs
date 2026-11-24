public class InternetSubscriber extends BaseSubscriber implements InternetAccount {
    private int speedLimitMbps;

    public InternetSubscriber(String operator, String tariff, int trafficMb, int speedLimitMbps) {
        super(operator, tariff, trafficMb);
        this.speedLimitMbps = speedLimitMbps;
    }

    @Override
    public void buyTrafficPackage(int mb, double price) {
        if (mb <= 0) {
            throw new IllegalArgumentException("Размер пакета должен быть больше нуля");
        }
        charge(price, "пакет " + mb + " МБ");
        addTraffic(mb);
    }

    @Override
    public void setSpeedLimit(int mbps) {
        checkOpen();
        if (mbps <= 0) {
            throw new IllegalArgumentException("Скорость должна быть больше нуля");
        }
        this.speedLimitMbps = mbps;
    }

    @Override
    public int getSpeedLimit() {
        return speedLimitMbps;
    }
}