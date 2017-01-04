package biz.lungo.authtask.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class EnterpriseAccount implements Parcelable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("CustomerNumber")
    @Expose
    private String customerNumber;
    @SerializedName("IsValid")
    @Expose
    private Boolean isValid;
    @SerializedName("LogoSasUrl")
    @Expose
    private String logoSasUrl;
    @SerializedName("Enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("Created")
    @Expose
    private String created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.accountName);
        dest.writeString(this.customerNumber);
        dest.writeValue(this.isValid);
        dest.writeString(this.logoSasUrl);
        dest.writeValue(this.enabled);
        dest.writeString(this.created);
    }

    private EnterpriseAccount(Parcel in) {
        this.id = in.readString();
        this.accountName = in.readString();
        this.customerNumber = in.readParcelable(Object.class.getClassLoader());
        this.isValid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.logoSasUrl = in.readParcelable(Object.class.getClassLoader());
        this.enabled = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.created = in.readString();
    }

    public static final Parcelable.Creator<EnterpriseAccount> CREATOR = new Parcelable.Creator<EnterpriseAccount>() {
        public EnterpriseAccount createFromParcel(Parcel source) {
            return new EnterpriseAccount(source);
        }

        public EnterpriseAccount[] newArray(int size) {
            return new EnterpriseAccount[size];
        }
    };
}