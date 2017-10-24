package ch.hsr.mge.gadgeothek.http;


import static ch.hsr.mge.gadgeothek.http.Endpoint.get;
import static ch.hsr.mge.gadgeothek.http.Endpoint.post;

public class MockedGadgeothekBackend {
    private final HttpProxy httpProxy;

    public MockedGadgeothekBackend(HttpProxy httpProxy) {
        this.httpProxy = httpProxy;
    }

    public void givenRegisterSuccessful() {
        httpProxy.mockCall(post("/public/register"), "true");
    }

    public void givenRegisterUnsuccessful() {
        httpProxy.mockCall(post("/public/register"), 500);
    }

    public void givenLoginSuccessful(String customerId, String securityToken) {
        String loginResponse = "{\"customerId\": \"" + customerId + "\", \"securityToken\": \"" + securityToken + "\"}";
        httpProxy.mockCall(post("/public/login"), loginResponse);
    }

    public void givenEmptyLoans() {
        httpProxy.mockCall(get("/public/loans"), "[]");
    }

    public void givenEmptyReservations() {
        httpProxy.mockCall(get("/public/reservations"), "[]");
    }

}
