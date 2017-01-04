package biz.lungo.authtask.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileInfo implements Parcelable {

    @SerializedName("MailAddress")
    @Expose
    private String mailAddress;
    @SerializedName("Salutation")
    @Expose
    private String salutation;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("CompanyName")
    @Expose
    private String companyName;
    @SerializedName("Street")
    @Expose
    private String street;
    @SerializedName("Street2")
    @Expose
    private String street2;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("ZipCode")
    @Expose
    private String zipCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("AvatarSasUrl")
    @Expose
    private String avatarSasUrl;
    @SerializedName("CultureString")
    @Expose
    private String cultureString;
    @SerializedName("ActivationHandler")
    @Expose
    private String activationHandler;
    @SerializedName("IsValid")
    @Expose
    private Boolean isValid;
    @SerializedName("IsLocked")
    @Expose
    private Boolean isLocked;
    @SerializedName("IsActivated")
    @Expose
    private Boolean isActivated;
    @SerializedName("Created")
    @Expose
    private String created;
    @SerializedName("ChangePasswordOnNextLogin")
    @Expose
    private Boolean changePasswordOnNextLogin;
    @SerializedName("IsInOrganization")
    @Expose
    private Boolean isInOrganization;
    @SerializedName("EnterpriseAccount")
    @Expose
    private EnterpriseAccount enterpriseAccount;

    public String getMailAddress() {
        return mailAddress == null ? "" : mailAddress;
    }

    public String getSalutation() {
        return salutation == null ? "" : salutation;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public String getCountry() {
        return country == null ? "" : country;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public String getAvatarSasUrl() {
        return avatarSasUrl == null ? "" : avatarSasUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mailAddress);
        dest.writeString(this.salutation);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.companyName);
        dest.writeString(this.street);
        dest.writeString(this.street2);
        dest.writeString(this.city);
        dest.writeString(this.zipCode);
        dest.writeString(this.country);
        dest.writeString(this.phone);
        dest.writeString(this.password);
        dest.writeString(this.avatarSasUrl);
        dest.writeString(this.cultureString);
        dest.writeString(this.activationHandler);
        dest.writeValue(this.isValid);
        dest.writeValue(this.isLocked);
        dest.writeValue(this.isActivated);
        dest.writeString(this.created);
        dest.writeValue(this.changePasswordOnNextLogin);
        dest.writeValue(this.isInOrganization);
        dest.writeParcelable(this.enterpriseAccount, 0);
    }

    private ProfileInfo(Parcel in) {
        this.mailAddress = in.readString();
        this.salutation = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.companyName = in.readString();
        this.street = in.readParcelable(Object.class.getClassLoader());
        this.street2 = in.readParcelable(Object.class.getClassLoader());
        this.city = in.readParcelable(Object.class.getClassLoader());
        this.zipCode = in.readParcelable(Object.class.getClassLoader());
        this.country = in.readString();
        this.phone = in.readString();
        this.password = in.readParcelable(Object.class.getClassLoader());
        this.avatarSasUrl = in.readString();
        this.cultureString = in.readParcelable(Object.class.getClassLoader());
        this.activationHandler = in.readParcelable(Object.class.getClassLoader());
        this.isValid = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isLocked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isActivated = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.created = in.readString();
        this.changePasswordOnNextLogin = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isInOrganization = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.enterpriseAccount = in.readParcelable(EnterpriseAccount.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProfileInfo> CREATOR = new Parcelable.Creator<ProfileInfo>() {
        public ProfileInfo createFromParcel(Parcel source) {
            return new ProfileInfo(source);
        }

        public ProfileInfo[] newArray(int size) {
            return new ProfileInfo[size];
        }
    };
}