package io.osvaldocabral.appbarbearia.Model;

import java.util.UUID;

public class ServiceBarber {

    public ServiceBarber(String name, Float value, String id_user) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.value = value;
        this.id_user = id_user;
    }

    private String uuid;
    private String name;
    private Float value;
    private String id_user;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
