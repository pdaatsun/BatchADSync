package org.sch.issecurity.iam.tools.ADSync;

/**
 * Created by XiChen on 2/20/2017.
 */
import com.unboundid.ldap.sdk.Attribute;
import org.sch.issecurity.iam.tools.ADSync.entity.ADUserCLEntity;
import org.sch.issecurity.iam.tools.ADSync.entity.ADUserEntity;
import org.sch.issecurity.iam.tools.ADSync.jpa.ADUserCLRepository;
import org.sch.issecurity.iam.tools.ADSync.jpa.ADUserRepository;
import org.adsync4j.spi.EntryProcessor;
import org.sch.issecurity.iam.tools.ADSync.utils.ADSyncUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import static org.adsync4j.impl.UUIDUtils.bytesToUUID;

/**
 * This class implements the {@link EntryProcessor} callback interface the processNew/Changed/Deleted methods of which are
 * invoked during the synchronization process. This implementation simply persists the new, updates the changed,
 * and removes the deleted entities in the target database.
 */
@Component
public class ADUserProcessor implements EntryProcessor<Attribute> {

    private final static Logger LOG = LoggerFactory.getLogger(ADUserProcessor.class);

    private final ADUserRepository _adUserRepository;
    private final ADUserCLRepository _adUserCLRepository;

    @Autowired
    public ADUserProcessor(ADUserRepository adUserRepository, ADUserCLRepository adUserCLRepository) {
        _adUserRepository = adUserRepository;
        _adUserCLRepository = adUserCLRepository;
    }
    
    public void processNew(List<Attribute> entry) {
        HashMap<String, Object> adUserEntitiesHM = null;

        try {
            adUserEntitiesHM = attributesToADUserEntity(entry);
        } catch (ParseException pe) {
            return;
        }
        if ((adUserEntitiesHM==null) || (adUserEntitiesHM.isEmpty())) return;
        ADUserEntity adUserEntity = (ADUserEntity) adUserEntitiesHM.get("ADUserEntity");
        ADUserCLEntity adUserCLEntity = (ADUserCLEntity) adUserEntitiesHM.get("ADUserCLEntity");
        adUserCLEntity.setChangeType("NEW");
        adUserCLEntity.setSyncedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        LOG.debug("processNew of sAMAccountName: {}", adUserEntity.getsAMAccountName());

        ADUserEntity persistentADUserEntity = _adUserRepository.save(adUserEntity);
        LOG.debug("New AD User: {}", persistentADUserEntity);
        ADUserCLEntity persistentADUserCLEntity = _adUserCLRepository.save(adUserCLEntity);
        LOG.debug("New AD User CL: {}", persistentADUserCLEntity);
    }
    
    public void processChanged(List<Attribute> entry) {
        HashMap<String, Object> adUserEntitiesHM = null;
        try {
            adUserEntitiesHM = attributesToADUserEntity(entry);
        } catch (ParseException pe) {
            return;
        }
        if ((adUserEntitiesHM==null) || (adUserEntitiesHM.isEmpty())) return;
        ADUserEntity transientADUserEntity = (ADUserEntity) adUserEntitiesHM.get("ADUserEntity");
        ADUserCLEntity adUserCLEntity = (ADUserCLEntity) adUserEntitiesHM.get("ADUserCLEntity");
        adUserCLEntity.setChangeType("UPDATE");
        adUserCLEntity.setSyncedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        ADUserEntity persistentADUserEntity = _adUserRepository.findByObjectGUID(transientADUserEntity.getObjectGUID());
        if (persistentADUserEntity == null){
            processNew(entry);
            return;
        }
        LOG.debug("processChanged of sAMAccountName: {}", transientADUserEntity.getsAMAccountName());
        persistentADUserEntity = persistADUserEntityfromTransientADUserEntity(persistentADUserEntity, transientADUserEntity);
        _adUserRepository.save(persistentADUserEntity);
        LOG.debug("Updated AD User: {}", persistentADUserEntity);
        ADUserCLEntity persistentADUserCLEntity = _adUserCLRepository.save(adUserCLEntity);
        LOG.debug("Updated AD User CL: {}", persistentADUserCLEntity);
    }
    
    public void processDeleted(UUID entryId) {
        ADUserEntity persistentADUserEntity = _adUserRepository.findByObjectGUID(entryId.toString());
        if (persistentADUserEntity == null){
            return;
        }
        ADUserCLEntity persistentADUserCLEntity = copyADUserCLEntityfromADUserEntity(persistentADUserEntity);
        persistentADUserCLEntity.setChangeType("DELETE");
        persistentADUserCLEntity.setSyncedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        LOG.debug("processDeleted of sAMAccountName: {}", persistentADUserEntity.getsAMAccountName());
        _adUserRepository.delete(persistentADUserEntity);
        LOG.debug("Deleted AD User: {}", persistentADUserEntity);

        LOG.debug("Deleted AD User CL: {}", persistentADUserCLEntity);
    }

    private HashMap<String, Object> attributesToADUserEntity(List<Attribute> entry) throws ParseException {

        HashMap<String, Object> adUserEntitiesHM = new HashMap<String, Object>();

        ADUserEntity adUserEntity= new ADUserEntity();
        ADUserCLEntity adUserCLEntity = new ADUserCLEntity();

        UUID uuid = bytesToUUID(entry.get(0).getValueByteArray());
        adUserEntity.setObjectGUID(uuid.toString());
        adUserCLEntity.setObjectGUID(uuid.toString());

        String sAMAccountName = entry.get(1).getValue();
        adUserEntity.setsAMAccountName(sAMAccountName);
        adUserCLEntity.setsAMAccountName(sAMAccountName);

        String givenName = entry.get(2) == null ? null : entry.get(2).getValue();
        adUserEntity.setGivenName(givenName);
        adUserCLEntity.setGivenName(givenName);

        String sn = entry.get(3) == null ? null : entry.get(3).getValue();
        adUserEntity.setSn(sn);
        adUserCLEntity.setSn(sn);

        String displayName = entry.get(4) == null ? null : entry.get(4).getValue();
        adUserEntity.setDisplayName(displayName);
        adUserCLEntity.setDisplayName(displayName);

        String title = entry.get(5) == null ? null : entry.get(5).getValue();
        adUserEntity.setTitle(title);
        adUserCLEntity.setTitle(title);

        String mail = entry.get(6) == null ? null : entry.get(6).getValue();
        adUserEntity.setMail(mail);
        adUserCLEntity.setMail(mail);

        String employeeID = entry.get(7) == null ? null : entry.get(7).getValue();
        adUserEntity.setEmployeeId(employeeID);
        adUserCLEntity.setEmployeeId(employeeID);

        String employeeNumber = entry.get(8) == null ? null : entry.get(8).getValue();
        adUserEntity.setEmployeeNumber(employeeNumber);
        adUserCLEntity.setEmployeeNumber(employeeNumber);

        String manager = entry.get(9) == null ? null : entry.get(9).getValue();
        adUserEntity.setManager(manager);
        adUserCLEntity.setManager(manager);

        String department = entry.get(10) == null ? null : entry.get(10).getValue();
        adUserEntity.setDepartment(department);
        adUserCLEntity.setDepartment(department);

        Timestamp accountExpires = null;
        if ((entry.get(11) != null) && (entry.get(11).getValueAsLong() != null))
            accountExpires = ADSyncUtils.converADdateToTimestamp(entry.get(11).getValueAsLong());
        adUserEntity.setAccountExpires(accountExpires);
        adUserCLEntity.setAccountExpires(accountExpires);

        String company = entry.get(12) == null ? null : entry.get(12).getValue();
        adUserEntity.setCompany(company);
        adUserCLEntity.setCompany(company);

        String description = entry.get(13) == null ? null : entry.get(13).getValue();
        adUserEntity.setDescription(description);
        adUserCLEntity.setDescription(description);

        String distinguishedName = entry.get(14) == null ? null : entry.get(14).getValue();
        adUserEntity.setDistinguishedName(distinguishedName);
        adUserCLEntity.setDistinguishedName(distinguishedName);

        String homeDirectory = entry.get(15) == null ? null : entry.get(15).getValue();
        adUserEntity.setHomeDirectory(homeDirectory);
        adUserCLEntity.setHomeDirectory(homeDirectory);

        String homeDrive = entry.get(16) == null ? null : entry.get(16).getValue();
        adUserEntity.setHomeDrive(homeDrive);
        adUserCLEntity.setHomeDrive(homeDrive);

        String homeMDB = entry.get(17) == null ? null : entry.get(17).getValue();
        adUserEntity.setHomeMdb(homeMDB);
        adUserCLEntity.setHomeMdb(homeMDB);

        String homeMTA = entry.get(18) == null ? null : entry.get(18).getValue();
        adUserEntity.setHomeMta(homeMTA);
        adUserCLEntity.setHomeMta(homeMTA);

        String extensionAttribute3 = entry.get(19) == null ? null : entry.get(19).getValue();
        adUserEntity.setExtensionAttribute3(extensionAttribute3);
        adUserCLEntity.setExtensionAttribute3(extensionAttribute3);

        String info = entry.get(20) == null ? null : entry.get(20).getValue();
        adUserEntity.setInfo(info);
        adUserCLEntity.setInfo(info);

        String employeeType = entry.get(21) == null ? null : entry.get(21).getValue();
        adUserEntity.setEmployeeType(employeeType);
        adUserCLEntity.setEmployeeType(employeeType);

        String extensionAttribute4 = entry.get(22) == null ? null : entry.get(22).getValue();
        adUserEntity.setExtensionAttribute4(extensionAttribute4);
        adUserCLEntity.setExtensionAttribute4(extensionAttribute4);

        String extensionAttribute5 = entry.get(23) == null ? null : entry.get(23).getValue();
        adUserEntity.setExtensionAttribute5(extensionAttribute5);
        adUserCLEntity.setExtensionAttribute5(extensionAttribute5);

        String extensionAttribute7 = entry.get(24) == null ? null : entry.get(24).getValue();
        adUserEntity.setExtensionAttribute7(extensionAttribute7);
        adUserCLEntity.setExtensionAttribute7(extensionAttribute7);

        String extensionAttribute10 = entry.get(25) == null ? null : entry.get(25).getValue();
        adUserEntity.setExtensionAttribute10(extensionAttribute10);
        adUserCLEntity.setExtensionAttribute10(extensionAttribute10);

        Timestamp whenChanged = null;
        if ((entry.get(26) != null) && (entry.get(26).getValueAsDate() != null))
            whenChanged = new java.sql.Timestamp(entry.get(26).getValueAsDate().getTime());
        adUserEntity.setWhenChanged(whenChanged);
        adUserCLEntity.setWhenChanged(whenChanged);

        Timestamp whenCreated = null;
        if ((entry.get(27) != null) && (entry.get(27).getValueAsDate() != null))
            whenCreated = new java.sql.Timestamp(entry.get(27).getValueAsDate().getTime());
        adUserEntity.setWhenCreated(whenCreated);
        adUserCLEntity.setWhenCreated(whenCreated);

        String directReports = entry.get(28) == null ? null : Arrays.toString(entry.get(28).getValues());
        adUserEntity.setDirectReports(directReports);
        adUserCLEntity.setDirectReports(directReports);

        String proxyAddresses = entry.get(29) == null ? null : Arrays.toString(entry.get(29).getValues());
        adUserEntity.setProxyAddresses(proxyAddresses);
        adUserCLEntity.setProxyAddresses(proxyAddresses);

        String memberOf = entry.get(30) == null ? null : Arrays.toString(entry.get(30).getValues());
        adUserEntity.setMemberOf(memberOf);
        adUserCLEntity.setMemberOf(memberOf);

        String uSNChanged = entry.get(31) == null ? null : entry.get(31).getValue();
        adUserEntity.setuSnChanged(uSNChanged);
        adUserCLEntity.setuSnChanged(uSNChanged);

        String uSNCreated = entry.get(32) == null ? null : entry.get(32).getValue();
        adUserEntity.setuSnCreated(uSNCreated);
        adUserCLEntity.setuSnCreated(uSNCreated);

        String userAccountControl = entry.get(33) == null ? null : entry.get(33).getValue();
        adUserEntity.setuserAccountControl(userAccountControl);
        adUserCLEntity.setuserAccountControl(userAccountControl);

        String userPrincipalName = entry.get(34) == null ? null : entry.get(34).getValue();
        adUserEntity.setuserPrincipalName(userPrincipalName);
        adUserCLEntity.setuserPrincipalName(userPrincipalName);

        String msDSUserAccountDisabled = entry.get(35) == null ? null : entry.get(35).getValue();
        adUserEntity.setmsDSUserAccountDisabled(msDSUserAccountDisabled);
        adUserCLEntity.setmsDSUserAccountDisabled(msDSUserAccountDisabled);

        String serialNumber = entry.get(36) == null ? null : entry.get(36).getValue();
        adUserEntity.setserialNumber(serialNumber);
        adUserCLEntity.setserialNumber(serialNumber);

        String internationalISDNNumber = entry.get(37) == null ? null : entry.get(37).getValue();
        adUserEntity.setinternationalISDNNumber(internationalISDNNumber);
        adUserCLEntity.setinternationalISDNNumber(internationalISDNNumber);

        String x121Address = entry.get(38) == null ? null : entry.get(38).getValue();
        adUserEntity.setx121Address(x121Address);
        adUserCLEntity.setx121Address(x121Address);

        adUserEntitiesHM.put("ADUserEntity", adUserEntity);
        adUserEntitiesHM.put("ADUserCLEntity", adUserCLEntity);

        return adUserEntitiesHM;
    }


    private ADUserEntity persistADUserEntityfromTransientADUserEntity(ADUserEntity persistADUserEntity, ADUserEntity transientADUserEntity) {

        persistADUserEntity.setsAMAccountName(transientADUserEntity.getsAMAccountName());
        persistADUserEntity.setGivenName(transientADUserEntity.getGivenName());
        persistADUserEntity.setSn(transientADUserEntity.getSn());
        persistADUserEntity.setDisplayName(transientADUserEntity.getDisplayName());

        persistADUserEntity.setTitle(transientADUserEntity.getTitle());
        persistADUserEntity.setMail(transientADUserEntity.getMail());
        persistADUserEntity.setEmployeeId(transientADUserEntity.getEmployeeId());
        persistADUserEntity.setEmployeeNumber(transientADUserEntity.getEmployeeNumber());

        persistADUserEntity.setManager(transientADUserEntity.getManager());
        persistADUserEntity.setDepartment(transientADUserEntity.getDepartment());
        persistADUserEntity.setAccountExpires(transientADUserEntity.getAccountExpires());
        persistADUserEntity.setCompany(transientADUserEntity.getCompany());

        persistADUserEntity.setDescription(transientADUserEntity.getDescription());
        persistADUserEntity.setDistinguishedName(transientADUserEntity.getDistinguishedName());
        persistADUserEntity.setHomeDirectory(transientADUserEntity.getHomeDirectory());
        persistADUserEntity.setHomeDrive(transientADUserEntity.getHomeDrive());

        persistADUserEntity.setHomeMdb(transientADUserEntity.getHomeMdb());
        persistADUserEntity.setHomeMta(transientADUserEntity.getHomeMta());
        persistADUserEntity.setExtensionAttribute3(transientADUserEntity.getExtensionAttribute3());
        persistADUserEntity.setInfo(transientADUserEntity.getInfo());

        persistADUserEntity.setEmployeeType(transientADUserEntity.getEmployeeType());
        persistADUserEntity.setExtensionAttribute4(transientADUserEntity.getExtensionAttribute4());
        persistADUserEntity.setExtensionAttribute5(transientADUserEntity.getExtensionAttribute5());
        persistADUserEntity.setExtensionAttribute7(transientADUserEntity.getExtensionAttribute7());
        persistADUserEntity.setExtensionAttribute10(transientADUserEntity.getExtensionAttribute10());
        persistADUserEntity.setWhenChanged(transientADUserEntity.getWhenChanged());
        persistADUserEntity.setWhenCreated(transientADUserEntity.getWhenCreated());

        persistADUserEntity.setDirectReports(transientADUserEntity.getDirectReports());
        persistADUserEntity.setProxyAddresses(transientADUserEntity.getProxyAddresses());
        persistADUserEntity.setMemberOf(transientADUserEntity.getMemberOf());
        persistADUserEntity.setuSnChanged(transientADUserEntity.getuSnChanged());
        persistADUserEntity.setuSnCreated(transientADUserEntity.getuSnCreated());

        persistADUserEntity.setuserAccountControl(transientADUserEntity.getuserAccountControl());
        persistADUserEntity.setuserPrincipalName(transientADUserEntity.getuserPrincipalName());
        persistADUserEntity.setmsDSUserAccountDisabled(transientADUserEntity.getmsDSUserAccountDisabled());

        persistADUserEntity.setserialNumber(transientADUserEntity.getserialNumber());
        persistADUserEntity.setinternationalISDNNumber(transientADUserEntity.getinternationalISDNNumber());
        persistADUserEntity.setx121Address(transientADUserEntity.getx121Address());

        return persistADUserEntity;
    }

    private ADUserCLEntity copyADUserCLEntityfromADUserEntity(ADUserEntity adUserEntity) {

        ADUserCLEntity adUserCLEntity = new ADUserCLEntity();
        adUserCLEntity.setObjectGUID(adUserEntity.getObjectGUID());
        adUserCLEntity.setsAMAccountName(adUserEntity.getsAMAccountName());
        adUserCLEntity.setGivenName(adUserEntity.getGivenName());
        adUserCLEntity.setSn(adUserEntity.getSn());
        adUserCLEntity.setDisplayName(adUserEntity.getDisplayName());

        adUserCLEntity.setTitle(adUserEntity.getTitle());
        adUserCLEntity.setMail(adUserEntity.getMail());
        adUserCLEntity.setEmployeeId(adUserEntity.getEmployeeId());
        adUserCLEntity.setEmployeeNumber(adUserEntity.getEmployeeNumber());

        adUserCLEntity.setManager(adUserEntity.getManager());
        adUserCLEntity.setDepartment(adUserEntity.getDepartment());
        adUserCLEntity.setAccountExpires(adUserEntity.getAccountExpires());
        adUserCLEntity.setCompany(adUserEntity.getCompany());

        adUserCLEntity.setDescription(adUserEntity.getDescription());
        adUserCLEntity.setDistinguishedName(adUserEntity.getDistinguishedName());
        adUserCLEntity.setHomeDirectory(adUserEntity.getHomeDirectory());
        adUserCLEntity.setHomeDrive(adUserEntity.getHomeDrive());

        adUserCLEntity.setHomeMdb(adUserEntity.getHomeMdb());
        adUserCLEntity.setHomeMta(adUserEntity.getHomeMta());
        adUserCLEntity.setExtensionAttribute3(adUserEntity.getExtensionAttribute3());
        adUserCLEntity.setInfo(adUserEntity.getInfo());

        adUserCLEntity.setEmployeeType(adUserEntity.getEmployeeType());
        adUserCLEntity.setExtensionAttribute4(adUserEntity.getExtensionAttribute4());
        adUserCLEntity.setExtensionAttribute5(adUserEntity.getExtensionAttribute5());
        adUserCLEntity.setExtensionAttribute7(adUserEntity.getExtensionAttribute7());
        adUserCLEntity.setExtensionAttribute10(adUserEntity.getExtensionAttribute10());
        adUserCLEntity.setWhenChanged(adUserEntity.getWhenChanged());
        adUserCLEntity.setWhenCreated(adUserEntity.getWhenCreated());

        adUserCLEntity.setDirectReports(adUserEntity.getDirectReports());
        adUserCLEntity.setProxyAddresses(adUserEntity.getProxyAddresses());
        adUserCLEntity.setMemberOf(adUserEntity.getMemberOf());
        adUserCLEntity.setuSnChanged(adUserEntity.getuSnChanged());
        adUserCLEntity.setuSnCreated(adUserEntity.getuSnCreated());

        adUserCLEntity.setuserAccountControl(adUserEntity.getuserAccountControl());
        adUserCLEntity.setuserPrincipalName(adUserEntity.getuserPrincipalName());
        adUserCLEntity.setmsDSUserAccountDisabled(adUserEntity.getmsDSUserAccountDisabled());

        adUserCLEntity.setserialNumber(adUserEntity.getserialNumber());
        adUserCLEntity.setinternationalISDNNumber(adUserEntity.getinternationalISDNNumber());
        adUserCLEntity.setx121Address(adUserEntity.getx121Address());

        return adUserCLEntity;
    }
}
