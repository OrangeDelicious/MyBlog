package com.tianxu.shejimoshi.Adapter;

public class TVAdapter implements TV{

    Charge charge;
    public TVAdapter(Charge charge) {
        this.charge = charge;
    }

    @Override
    public void use110v() {
        charge.use220v();
    }
}
