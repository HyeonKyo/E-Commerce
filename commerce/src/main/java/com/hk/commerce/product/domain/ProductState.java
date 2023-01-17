package com.hk.commerce.product.domain;

public enum ProductState {
    SELLING {
        @Override
        public boolean canSelling() {
            return true;
        }
    },
    OUT_OF_STOCK, STOP_SELLING,
    ;
    public boolean canSelling() {
        return false;
    }
}
