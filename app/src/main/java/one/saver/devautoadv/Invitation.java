package one.saver.devautoadv;

/**
 * Created by Doron Yechezkel on 12/3/2017.
 */

public class Invitation {
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
    private int isRead;

    public Invitation() {
    }

    public Invitation(int indexNumber, String IMEI) {
        this.indexNumber = indexNumber;
        this.IMEI = IMEI;
    }

    public Invitation(int indexNumber, String IMEI, int makeIndex, int modelIndex, String make, String model) {
        this.indexNumber = indexNumber;
        this.IMEI = IMEI;
        this.makeIndex = makeIndex;
        this.modelIndex = modelIndex;
        this.make = make;
        this.model = model;
    }

    public Invitation(int indexNumber, String IMEI, int makeIndex, int modelIndex, String color, int minPrice, int maxPrice, int minMileage, int maxMileage) {
        this.indexNumber = indexNumber;
        this.IMEI = IMEI;
        this.makeIndex = makeIndex;
        this.modelIndex = modelIndex;
        this.color = color;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minMileage = minMileage;
        this.maxMileage = maxMileage;
    }

    public Invitation(int indexNumber, String IMEI, int makeIndex, int modelIndex, String make, String model, String color, int minPrice, int maxPrice, int minMileage, int maxMileage, String image_1, String image_2, int isRead) {
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
        this.isRead = isRead;
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

    public int getIsRead() {
        return isRead;
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

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invitation that = (Invitation) o;

        if (indexNumber != that.indexNumber) return false;
        if (makeIndex != that.makeIndex) return false;
        if (modelIndex != that.modelIndex) return false;
        if (minPrice != that.minPrice) return false;
        if (maxPrice != that.maxPrice) return false;
        if (minMileage != that.minMileage) return false;
        if (maxMileage != that.maxMileage) return false;
        if (isRead != that.isRead) return false;
        if (IMEI != null ? !IMEI.equals(that.IMEI) : that.IMEI != null) return false;
        if (make != null ? !make.equals(that.make) : that.make != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (image_1 != null ? !image_1.equals(that.image_1) : that.image_1 != null) return false;
        return image_2 != null ? image_2.equals(that.image_2) : that.image_2 == null;
    }

    @Override
    public int hashCode() {
        int result = indexNumber;
        result = 31 * result + (IMEI != null ? IMEI.hashCode() : 0);
        result = 31 * result + makeIndex;
        result = 31 * result + modelIndex;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + minPrice;
        result = 31 * result + maxPrice;
        result = 31 * result + minMileage;
        result = 31 * result + maxMileage;
        result = 31 * result + (image_1 != null ? image_1.hashCode() : 0);
        result = 31 * result + (image_2 != null ? image_2.hashCode() : 0);
        result = 31 * result + isRead;
        return result;
    }

    @Override
    public String toString() {
        return "Invitation{" +
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
                ", isRead=" + isRead +
                '}';
    }
}
