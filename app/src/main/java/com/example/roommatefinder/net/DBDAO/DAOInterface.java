package com.example.roommatefinder.net.DBDAO;

public interface DAOInterface<C1, C2, Q1, Q2> {
    C2 Create(C1 request);
    C2 Update(C1 request);
    Q2 Delete(Q1 request);
    Q2 Query(Q1 request);
}
