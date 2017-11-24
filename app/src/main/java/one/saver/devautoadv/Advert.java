package one.saver.devautoadv;


public class Advert {
    private int indexNumber;
    private String IMEI;
    private int makeIndex;
    private int modelIndex;
    private String make;
    private String model;
    private String color;
    private int minPrice;
    private int maxPrice;
    private int minMileage;
    private int maxMileage;
    private String image_1;
    private String image_2;
    private int isMain;

    public Advert() {
    }

    public Advert(int indexNumber, String IMEI, int makeIndex, int modelIndex, String make, String model, String color, int minPrice, int maxPrice, int minMileage, int maxMileage, String image_1, String image_2, int isMain) {
        this.indexNumber = indexNumber;
        this.IMEI = IMEI;
        this.makeIndex = makeIndex;
        this.modelIndex = modelIndex;
        this.make = make;
        this.model = model;
        this.color = color;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.isMain = isMain;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public int getMakeIndex() {
        return makeIndex;
    }

    public int getModelIndex() {
        return modelIndex;
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

    public int getIsMain() {
        return isMain;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public void setMakeIndex(int makeIndex) {
        this.makeIndex = makeIndex;
    }

    public void setModelIndex(int modelIndex) {
        this.modelIndex = modelIndex;
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

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advert advert = (Advert) o;

        if (indexNumber != advert.indexNumber) return false;
        if (makeIndex != advert.makeIndex) return false;
        if (modelIndex != advert.modelIndex) return false;
        if (minPrice != advert.minPrice) return false;
        if (maxPrice != advert.maxPrice) return false;
        if (minMileage != advert.minMileage) return false;
        if (maxMileage != advert.maxMileage) return false;
        if (isMain != advert.isMain) return false;
        if (!IMEI.equals(advert.IMEI)) return false;
        if (!make.equals(advert.make)) return false;
        if (!model.equals(advert.model)) return false;
        if (!color.equals(advert.color)) return false;
        if (!image_1.equals(advert.image_1)) return false;
        return image_2.equals(advert.image_2);

    }

    @Override
    public int hashCode() {
        int result = indexNumber;
        result = 31 * result + IMEI.hashCode();
        result = 31 * result + makeIndex;
        result = 31 * result + modelIndex;
        result = 31 * result + make.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + minPrice;
        result = 31 * result + maxPrice;
        result = 31 * result + minMileage;
        result = 31 * result + maxMileage;
        result = 31 * result + image_1.hashCode();
        result = 31 * result + image_2.hashCode();
        result = 31 * result + isMain;
        return result;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "indexNumber=" + indexNumber +
                ", IMEI='" + IMEI + '\'' +
                ", makeIndex=" + makeIndex +
                ", modelIndex=" + modelIndex +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minMileage=" + minMileage +
                ", maxMileage=" + maxMileage +
                ", image_1='" + image_1 + '\'' +
                ", image_2='" + image_2 + '\'' +
                ", isActive=" + isMain +
                '}';
    }
}