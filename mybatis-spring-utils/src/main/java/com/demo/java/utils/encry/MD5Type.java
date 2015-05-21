package com.demo.java.utils.encry;

public enum MD5Type {

    MD2("MD2"), MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");

    private MD5Type(String algorithms) {
        this.algorithms = algorithms;
    }

    private String algorithms;

    /**
     * 
     * 加密类型.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    public String getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(String algorithms) {
        this.algorithms = algorithms;
    }
}
