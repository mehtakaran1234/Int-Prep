package com.k2senterprise.codility.task2;



import com.codility.alert.Alert;
import com.codility.alert.AlertRepository;
import com.codility.audit.AuditService;
import com.codility.audit.DuplicatedAuditException;
import com.codility.log.Log;
import com.codility.log.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonitoringService {
    private final LogRepository logRepository;
    private final AlertRepository alertRepository;
    private final AuditService auditService;


    MonitoringService(LogRepository logRepository, AlertRepository alertRepository, AuditService auditService) {
        this.logRepository = logRepository;
        this.alertRepository = alertRepository;
        this.auditService = auditService;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void alert(Alert alert) throws DuplicatedAuditException {

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void log(Log log) throws DuplicatedAuditException {

    }
}


