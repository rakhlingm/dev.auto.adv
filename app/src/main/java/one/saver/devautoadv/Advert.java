package one.saver.devautoadv;


public class Advert {
    private int indexNumber;
    private String IMEI;
    private String make;
    private String model;
    private String color;
    private int minPrice;
    private int maxPrice;
    private int minMileage;
    private int maxMileage;
    private String image_1;
    private String image_2;

    public Advert() {
    }

    public Advert(int indexNumber, String IMEI, String make, String model, String color, int minPrice, int maxPrice, int minMileage, int maxMileage, String image_1, String image_2) {
        this.indexNumber = indexNumber;
        this.IMEI = IMEI;
        this.make = make;
        this.model = model;
        this.color = color;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
        this.image_1 = image_1;
        this.image_2 = image_2;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinMileage() {
        return minMileage;
    }

    public int getMaxMileage() {
        return maxMileage;
    }

    public String getImage_1() {
        return image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinMileage(int minMileage) {
        this.minMileage = minMileage;
    }

    public void setMaxMileage(int maxMileage) {
        this.maxMileage = maxMileage;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "indexNumber=" + indexNumber +
                ", IMEI='" + IMEI + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minMileage=" + minMileage +
                ", maxMileage=" + maxMileage +
                ", image_1='" + image_1 + '\'' +
                ", image_2='" + image_2 + '\'' +
                '}';
    }
}
