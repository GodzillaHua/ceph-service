package io.cat.ceph.web.form;

import javax.validation.constraints.NotNull;

/**
 * @author GodzillaHua
 **/
public class CreateCephUserForm {

    @NotNull
    private String name;

    @NotNull
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
