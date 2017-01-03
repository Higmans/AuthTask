package biz.lungo.authtask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileInfo {

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
    private Object street;
    @SerializedName("Street2")
    @Expose
    private Object street2;
    @SerializedName("City")
    @Expose
    private Object city;
    @SerializedName("ZipCode")
    @Expose
    private Object zipCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Password")
    @Expose
    private Object password;
    @SerializedName("AvatarSasUrl")
    @Expose
    private String avatarSasUrl;
    @SerializedName("CultureString")
    @Expose
    private Object cultureString;
    @SerializedName("ActivationHandler")
    @Expose
    private Object activationHandler;
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
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Object getStreet() {
        return street;
    }

    public void setStreet(Object street) {
        this.street = street;
    }

    public Object getStreet2() {
        return street2;
    }

    public void setStreet2(Object street2) {
        this.street2 = street2;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getZipCode() {
        return zipCode;
    }

    public void setZipCode(Object zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public String getAvatarSasUrl() {
        return avatarSasUrl;
    }

    public void setAvatarSasUrl(String avatarSasUrl) {
        this.avatarSasUrl = avatarSasUrl;
    }

    public Object getCultureString() {
        return cultureString;
    }

    public void setCultureString(Object cultureString) {
        this.cultureString = cultureString;
    }

    public Object getActivationHandler() {
        return activationHandler;
    }

    public void setActivationHandler(Object activationHandler) {
        this.activationHandler = activationHandler;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getChangePasswordOnNextLogin() {
        return changePasswordOnNextLogin;
    }

    public void setChangePasswordOnNextLogin(Boolean changePasswordOnNextLogin) {
        this.changePasswordOnNextLogin = changePasswordOnNextLogin;
    }

    public Boolean getIsInOrganization() {
        return isInOrganization;
    }

    public void setIsInOrganization(Boolean isInOrganization) {
        this.isInOrganization = isInOrganization;
    }

    public EnterpriseAccount getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(EnterpriseAccount enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public class EnterpriseAccount {

        @SerializedName("Id")
        @Expose
        private String id;
        @SerializedName("AccountName")
        @Expose
        private String accountName;
        @SerializedName("CustomerNumber")
        @Expose
        private Object customerNumber;
        @SerializedName("IsValid")
        @Expose
        private Boolean isValid;
        @SerializedName("LogoSasUrl")
        @Expose
        private Object logoSasUrl;
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

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public Object getCustomerNumber() {
            return customerNumber;
        }

        public void setCustomerNumber(Object customerNumber) {
            this.customerNumber = customerNumber;
        }

        public Boolean getIsValid() {
            return isValid;
        }

        public void setIsValid(Boolean isValid) {
            this.isValid = isValid;
        }

        public Object getLogoSasUrl() {
            return logoSasUrl;
        }

        public void setLogoSasUrl(Object logoSasUrl) {
            this.logoSasUrl = logoSasUrl;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }
}