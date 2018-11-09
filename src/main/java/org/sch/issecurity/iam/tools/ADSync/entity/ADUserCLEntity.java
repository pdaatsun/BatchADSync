package org.sch.issecurity.iam.tools.ADSync.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by XiChen on 2/20/2017.
 */
@Entity
@Table(name = "AD_USER_CL", schema = "dbo", catalog = "ADUsers")
public class ADUserCLEntity {
    private Long clID;
    private String objectGUID;
    private String sAMAccountName;
    private String givenName;
    private String sn;
    private String displayName;
    private String title;
    private String mail;
    private String employeeId;
    private String employeeNumber;
    private String manager;
    private String department;
    private Timestamp accountExpires;
    private String company;
    private String description;
    private String distinguishedName;
    private String homeDirectory;
    private String homeDrive;
    private String homeMdb;
    private String homeMta;
    private String extensionAttribute3;
    private String info;
    private String employeeType;
    private String extensionAttribute4;
    private String extensionAttribute5;
    private String extensionAttribute7;
    private String extensionAttribute10;
    private Timestamp whenChanged;
    private Timestamp whenCreated;
    private String directReports;
    private String proxyAddresses;
    private String memberOf;
    private String uSnChanged;
    private String uSnCreated;
    private String userAccountControl;
    private String userPrincipalName;
    private String msDSUserAccountDisabled;
    private String serialNumber;
    private String internationalISDNNumber;
    private String x121Address;
    private String changeType;
    private Timestamp syncedAt;

    @Id
    @Column(name = "clID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCLID() {
        return clID;
    }
    public void setCLID(Long clID) {
        this.clID = clID;
    }

    @Column(name = "objectGUID", nullable = false, length = 256)
    public String getObjectGUID() {
        return objectGUID;
    }

    public void setObjectGUID(String objectGUID) {
        this.objectGUID = objectGUID;
    }

    @Basic
    @Column(name = "sAMAccountName", nullable = false, length = 256)
    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    @Basic
    @Column(name = "givenName", nullable = true, length = 256)
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @Basic
    @Column(name = "sn", nullable = true, length = 256)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Basic
    @Column(name = "displayName", nullable = true, length = 256)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 512)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 256)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "employeeID", nullable = true, length = 512)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "SSN", nullable = true, length = 512)
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Basic
    @Column(name = "manager", nullable = true, length = 512)
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "department", nullable = true, length = 512)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "accountExpires", nullable = true)
    public Timestamp getAccountExpires() {
        return accountExpires;
    }

    public void setAccountExpires(Timestamp accountExpires) {
        this.accountExpires = accountExpires;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 512)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 512)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "distinguishedName", nullable = false, length = 512)
    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    @Basic
    @Column(name = "homeDirectory", nullable = true, length = 512)
    public String getHomeDirectory() {
        return homeDirectory;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    @Basic
    @Column(name = "homeDrive", nullable = true, length = 256)
    public String getHomeDrive() {
        return homeDrive;
    }

    public void setHomeDrive(String homeDrive) {
        this.homeDrive = homeDrive;
    }

    @Basic
    @Column(name = "homeMDB", nullable = true, length = 512)
    public String getHomeMdb() {
        return homeMdb;
    }

    public void setHomeMdb(String homeMdb) {
        this.homeMdb = homeMdb;
    }

    @Basic
    @Column(name = "homeMTA", nullable = true, length = 512)
    public String getHomeMta() {
        return homeMta;
    }

    public void setHomeMta(String homeMta) {
        this.homeMta = homeMta;
    }

    @Basic
    @Column(name = "DOB", nullable = true, length = 512)
    public String getExtensionAttribute3() {
        return extensionAttribute3;
    }

    public void setExtensionAttribute3(String extensionAttribute3) {
        this.extensionAttribute3 = extensionAttribute3;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 4096)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "employeeType", nullable = true, length = 512)
    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Basic
    @Column(name = "extensionAttribute4", nullable = true, length = 512)
    public String getExtensionAttribute4() {
        return extensionAttribute4;
    }

    public void setExtensionAttribute4(String extensionAttribute4) {
        this.extensionAttribute4 = extensionAttribute4;
    }

    @Basic
    @Column(name = "extensionAttribute5", nullable = true, length = 512)
    public String getExtensionAttribute5() {
        return extensionAttribute5;
    }

    public void setExtensionAttribute5(String extensionAttribute5) {
        this.extensionAttribute5 = extensionAttribute5;
    }

    @Basic
    @Column(name = "extensionAttribute7", nullable = true, length = 512)
    public String getExtensionAttribute7() {
        return extensionAttribute7;
    }

    public void setExtensionAttribute7(String extensionAttribute7) {
        this.extensionAttribute7 = extensionAttribute7;
    }

    @Basic
    @Column(name = "extensionAttribute10", nullable = true, length = 512)
    public String getExtensionAttribute10() {
        return extensionAttribute10;
    }

    public void setExtensionAttribute10(String extensionAttribute10) {
        this.extensionAttribute10 = extensionAttribute10;
    }

    @Basic
    @Column(name = "whenChanged", nullable = true)
    public Timestamp getWhenChanged() {
        return whenChanged;
    }

    public void setWhenChanged(Timestamp whenChanged) {
        this.whenChanged = whenChanged;
    }

    @Basic
    @Column(name = "whenCreated", nullable = true)
    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }

    @Basic
    @Column(name = "directReports", nullable = true, length = 4096)
    public String getDirectReports() {
        return directReports;
    }

    public void setDirectReports(String directReports) {
        this.directReports = directReports;
    }

    @Basic
    @Column(name = "proxyAddresses", nullable = true, length = 4096)
    public String getProxyAddresses() {
        return proxyAddresses;
    }

    public void setProxyAddresses(String proxyAddresses) {
        this.proxyAddresses = proxyAddresses;
    }

    @Basic
    @Column(name = "memberOf", nullable = true, length = 4096)
    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }

    @Column(name = "uSNChanged", nullable = false, length = 256)
    public String getuSnChanged() {
        return uSnChanged;
    }

    public void setuSnChanged(String uSnChanged) {
        this.uSnChanged = uSnChanged;
    }

    @Column(name = "uSNCreated", nullable = false, length = 256)
    public String getuSnCreated() {
        return uSnCreated;
    }

    public void setuSnCreated(String uSnCreated) {
        this.uSnCreated = uSnCreated;
    }

    @Basic
    @Column(name = "userAccountControl", nullable = true, length = 256)
    public String getuserAccountControl() {
        return userAccountControl;
    }

    public void setuserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }

    @Basic
    @Column(name = "userPrincipalName", nullable = true, length = 256)
    public String getuserPrincipalName() {
        return userPrincipalName;
    }

    public void setuserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    @Basic
    @Column(name = "msDSUserAccountDisabled", nullable = true, length = 256)
    public String getmsDSUserAccountDisabled() {
        return msDSUserAccountDisabled;
    }

    public void setmsDSUserAccountDisabled(String msDSUserAccountDisabled) {
        this.msDSUserAccountDisabled = msDSUserAccountDisabled;
    }

    @Basic
    @Column(name = "MSOID", nullable = true, length = 256)
    public String getserialNumber() {
        return serialNumber;
    }

    public void setserialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "PCHAID", nullable = true, length = 256)
    public String getinternationalISDNNumber() {
        return internationalISDNNumber;
    }

    public void setinternationalISDNNumber(String internationalISDNNumber) {
        this.internationalISDNNumber = internationalISDNNumber;
    }

    @Basic
    @Column(name = "SHCEmployeeID", nullable = true, length = 256)
    public String getx121Address() {
        return x121Address;
    }

    public void setx121Address(String x121Address) {
        this.x121Address = x121Address;
    }

    @Basic
    @Column(name = "changeType", nullable = false, length = 256)
    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    @Basic
    @Column(name = "syncedAt", nullable = false)
    public Timestamp getSyncedAt() {
        return syncedAt;
    }

    public void setSyncedAt(Timestamp syncedAt) {
        this.syncedAt = syncedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ADUserCLEntity that = (ADUserCLEntity) o;

        if (clID != null ? !clID.equals(that.clID) : that.clID != null) return false;
        if (objectGUID != null ? !objectGUID.equals(that.objectGUID) : that.objectGUID != null) return false;
        if (sAMAccountName != null ? !sAMAccountName.equals(that.sAMAccountName) : that.sAMAccountName != null)
            return false;
        if (givenName != null ? !givenName.equals(that.givenName) : that.givenName != null) return false;
        if (sn != null ? !sn.equals(that.sn) : that.sn != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (employeeNumber != null ? !employeeNumber.equals(that.employeeNumber) : that.employeeNumber != null)
            return false;
        if (manager != null ? !manager.equals(that.manager) : that.manager != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (accountExpires != null ? !accountExpires.equals(that.accountExpires) : that.accountExpires != null)
            return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (distinguishedName != null ? !distinguishedName.equals(that.distinguishedName) : that.distinguishedName != null)
            return false;
        if (homeDirectory != null ? !homeDirectory.equals(that.homeDirectory) : that.homeDirectory != null)
            return false;
        if (homeDrive != null ? !homeDrive.equals(that.homeDrive) : that.homeDrive != null) return false;
        if (homeMdb != null ? !homeMdb.equals(that.homeMdb) : that.homeMdb != null) return false;
        if (homeMta != null ? !homeMta.equals(that.homeMta) : that.homeMta != null) return false;
        if (extensionAttribute3 != null ? !extensionAttribute3.equals(that.extensionAttribute3) : that.extensionAttribute3 != null)
            return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (employeeType != null ? !employeeType.equals(that.employeeType) : that.employeeType != null) return false;
        if (extensionAttribute4 != null ? !extensionAttribute4.equals(that.extensionAttribute4) : that.extensionAttribute4 != null)
            return false;
        if (whenChanged != null ? !whenChanged.equals(that.whenChanged) : that.whenChanged != null) return false;
        if (whenCreated != null ? !whenCreated.equals(that.whenCreated) : that.whenCreated != null) return false;
        if (directReports != null ? !directReports.equals(that.directReports) : that.directReports != null)
            return false;
        if (proxyAddresses != null ? !proxyAddresses.equals(that.proxyAddresses) : that.proxyAddresses != null)
            return false;
        if (memberOf != null ? !memberOf.equals(that.memberOf) : that.memberOf != null) return false;
        if (uSnChanged != null ? !uSnChanged.equals(that.uSnChanged) : that.uSnChanged != null) return false;
        if (uSnCreated != null ? !uSnCreated.equals(that.uSnCreated) : that.uSnCreated != null) return false;
        if (userAccountControl != null ? !userAccountControl.equals(that.userAccountControl) : that.userAccountControl != null) return false;
        if (userPrincipalName != null ? !userPrincipalName.equals(that.userPrincipalName) : that.userPrincipalName != null) return false;
        if (msDSUserAccountDisabled != null ? !msDSUserAccountDisabled.equals(that.msDSUserAccountDisabled) : that.msDSUserAccountDisabled != null) return false;
        if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;
        if (internationalISDNNumber != null ? !internationalISDNNumber.equals(that.internationalISDNNumber) : that.internationalISDNNumber != null) return false;
        if (x121Address != null ? !x121Address.equals(that.x121Address) : that.x121Address != null) return false;
        if (changeType != null ? !changeType.equals(that.changeType) : that.changeType != null) return false;
        if (syncedAt != null ? !syncedAt.equals(that.syncedAt) : that.syncedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clID != null ? clID.hashCode() : 0;
        result = 31 * result + (objectGUID != null ? objectGUID.hashCode() : 0);
        result = 31 * result + (sAMAccountName != null ? sAMAccountName.hashCode() : 0);
        result = 31 * result + (givenName != null ? givenName.hashCode() : 0);
        result = 31 * result + (sn != null ? sn.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (employeeNumber != null ? employeeNumber.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (accountExpires != null ? accountExpires.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (distinguishedName != null ? distinguishedName.hashCode() : 0);
        result = 31 * result + (homeDirectory != null ? homeDirectory.hashCode() : 0);
        result = 31 * result + (homeDrive != null ? homeDrive.hashCode() : 0);
        result = 31 * result + (homeMdb != null ? homeMdb.hashCode() : 0);
        result = 31 * result + (homeMta != null ? homeMta.hashCode() : 0);
        result = 31 * result + (extensionAttribute3 != null ? extensionAttribute3.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        result = 31 * result + (extensionAttribute4 != null ? extensionAttribute4.hashCode() : 0);
        result = 31 * result + (whenChanged != null ? whenChanged.hashCode() : 0);
        result = 31 * result + (whenCreated != null ? whenCreated.hashCode() : 0);
        result = 31 * result + (directReports != null ? directReports.hashCode() : 0);
        result = 31 * result + (proxyAddresses != null ? proxyAddresses.hashCode() : 0);
        result = 31 * result + (memberOf != null ? memberOf.hashCode() : 0);
        result = 31 * result + (uSnChanged != null ? uSnChanged.hashCode() : 0);
        result = 31 * result + (uSnCreated != null ? uSnCreated.hashCode() : 0);
        result = 31 * result + (userAccountControl != null ? userAccountControl.hashCode() : 0);
        result = 31 * result + (userPrincipalName != null ? userPrincipalName.hashCode() : 0);
        result = 31 * result + (msDSUserAccountDisabled != null ? msDSUserAccountDisabled.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (internationalISDNNumber != null ? internationalISDNNumber.hashCode() : 0);
        result = 31 * result + (x121Address != null ? x121Address.hashCode() : 0);
        result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
        result = 31 * result + (syncedAt != null ? syncedAt.hashCode() : 0);
        return result;
    }
}
