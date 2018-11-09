package org.sch.issecurity.iam.tools.ADSync.jpa;

/**
 * Created by XiChen on 2/20/2017.
 */

import org.sch.issecurity.iam.tools.ADSync.entity.DomainControllerAffiliation;
import org.adsync4j.spi.DCARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.sch.issecurity.iam.tools.ADSync.jpa.JpaDCARepositoryAdapter.BEAN_NAME;

/**
 * This class wraps a {@link DomainControllerAffiliationRepository} and adapts it to the {@link DCARepository} interface which
 * is required by ADSync4J.
 */
@Component(BEAN_NAME)
public class JpaDCARepositoryAdapter implements DCARepository<String, DomainControllerAffiliation> {

    public static final String BEAN_NAME = "affiliationRepository";

    private final DomainControllerAffiliationRepository _jpaRepository;

    @Autowired
    public JpaDCARepositoryAdapter(DomainControllerAffiliationRepository jpaRepository) {
        _jpaRepository = jpaRepository;
    }

    public DomainControllerAffiliation load(String key) {
        return _jpaRepository.findOne(key);
    }

    public DomainControllerAffiliation save(DomainControllerAffiliation dca) {
        return _jpaRepository.save(dca);
    }
}