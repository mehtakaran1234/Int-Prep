package com.k2senterprise.codility.task2;

import java.util.List;

public interface AuditService {
    void collectAll(List<Message> auditable);

    void collect(Object auditable) throws DuplicatedAuditException;
}

