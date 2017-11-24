package one.saver.devautoadv;

/**
 * Created by Doron Yechezkel on 11/24/2017.
 */

public class Query {
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
    private int isActive;

    public Query() {
    }

    public Query(int indexNumber, String IMEI, int makeIndex, int modelIndex, String make, String model, String color, int minPrice, int maxPrice, int minMileage, int maxMileage, String image_1, String image_2, int isActive) {
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
        this.isActive = isActive;
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

    public int getIsActive() {
        return isActive;
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (indexNumber != query.indexNumber) return false;
        if (makeIndex != query.makeIndex) return false;
        if (modelIndex != query.modelIndex) return false;
        if (minPrice != query.minPrice) return false;
        if (maxPrice != query.maxPrice) return false;
        if (minMileage != query.minMileage) return false;
        if (maxMileage != query.maxMileage) return false;
        if (isActive != query.isActive) return false;
        if (IMEI != null ? !IMEI.equals(query.IMEI) : query.IMEI != null) return false;
        if (make != null ? !make.equals(query.make) : query.make != null) return false;
        if (model != null ? !model.equals(query.model) : query.model != null) return false;
        if (color != null ? !color.equals(query.color) : query.color != null) return false;
        if (image_1 != null ? !image_1.equals(query.image_1) : query.image_1 != null) return false;
        return image_2 != null ? image_2.equals(query.image_2) : query.image_2 == null;

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
        result = 31 * result + isActive;
        return result;
    }

    @Override
    public String toString() {
        return "Query{" +
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
                ", isActive=" + isActive +
                '}';
    }
}