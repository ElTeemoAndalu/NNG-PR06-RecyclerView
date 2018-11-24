package es.iessaladillo.pedrojoya.pr05.data.local.model;

import androidx.annotation.Nullable;

public class User {
    private int id;
    private Avatar avatar;
    private String name, email, phone, address, web;

    public User() {
    }

    public User(int id, Avatar avatar, String name, String email, String phone, String address, String web) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.web = web;
    }

    public void editUser(User user) {
        this.avatar = user.avatar;
        this.name = user.name;
        this.email = user.email;
        this.phone = user.phone;
        this.address = user.address;
        this.web = user.web;


    }

    public int getId() {
        return id;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getWeb() {
        return web;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;
        if (obj instanceof User
                && name.equals(((User) obj).name)
                && avatar.equals(((User) obj).avatar)
                && email.equals(((User) obj).email)
                && phone.equals(((User) obj).phone)
                && address.equals(((User) obj).address)
                && web.equals(((User) obj).web)) {

            result = true;

        }
        return result;
    }
}
