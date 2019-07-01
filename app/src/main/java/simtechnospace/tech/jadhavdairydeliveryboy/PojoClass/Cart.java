package simtechnospace.tech.jadhavdairydeliveryboy.PojoClass;

public class Cart {
    public static final String TABLE_NAME = "product_cart";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_CUSTOMER_NAME = "customer_name";
    public static final String COLUMN_CUSTOMER_DELIVERY_ADDRESS = "customer_delivery_address";
    public static final String COLUMN_CUSTOMER_DELIVERY_STATUS = "customer_delivery_status";
    public static final String COLUMN_CUSTOMER_REQUIREMENTS = "customer_requirements";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id, mDeliveryStatus;
    private String mCustomerId, mCustomerName, mDeliveryAddress, mRequirements, mTimeStamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_ID + " TEXT,"
                    + COLUMN_CUSTOMER_NAME + " TEXT, "
                    + COLUMN_CUSTOMER_DELIVERY_ADDRESS + " TEXT, "
                    + COLUMN_CUSTOMER_DELIVERY_STATUS + " INTEGER DEFAULT 0, "
                    + COLUMN_CUSTOMER_REQUIREMENTS + " TEXT, "
                    + COLUMN_TIMESTAMP + " DATE DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Cart() {

    }

    public Cart(int id, String customerId, String customerName, String deliveryAddress, String requirements, String timeStamp, int deliveryStatus) {
        this.id = id;
        this.mCustomerId = customerId;
        this.mCustomerName = customerName;
        this.mDeliveryAddress = deliveryAddress;
        this.mRequirements = requirements;
        this.mTimeStamp = timeStamp;
        this.mDeliveryStatus = deliveryStatus;
    }

    public int getDeliveryStatus() {
        return mDeliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.mDeliveryStatus = deliveryStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(String customerId) {
        this.mCustomerId = customerId;
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        this.mCustomerName = customerName;
    }

    public String getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.mDeliveryAddress = deliveryAddress;
    }

    public String getRequirements() {
        return mRequirements;
    }

    public void setRequirements(String requirements) {
        this.mRequirements = requirements;
    }

    public String getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.mTimeStamp = timeStamp;
    }

}