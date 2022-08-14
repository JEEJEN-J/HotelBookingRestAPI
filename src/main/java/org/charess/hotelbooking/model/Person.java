package org.charess.hotelbooking.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person extends ID implements Serializable {

    @NotEmpty
    @Size(min = 2, max = 200)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 200)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "phone", length = 30)
    private String phone;

    @Size(min = 5, max = 200)
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "location_address")
    private Location locationAddress;

    @Column(name = "text_address")
    private String textAddress;

    @Transient
    private String fullname;

    @Transient
    private String alias;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Person() {
    }

    public String getLastName() {
        return lastName != null ? formatHaitianName(lastName) : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(Location locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getTextAddress() {
        return textAddress;
    }

    public void setTextAddress(String textAddress) {
        this.textAddress = textAddress;
    }


    private LocalDate transform(Date date) {
        LocalDate localDate = null;
        if (date != null) {
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            localDate = localDateTime.toLocalDate();
        }
        return localDate;
    }

    public String getFullname() {
        return formatHaitianName(lastName).toUpperCase() + ", " + formatHaitianName(firstName);
    }

    public String getAlias() {
        String name = lastName + " " + firstName;
        name = name.replace("-", " ");
        String[] names = name.split(" ");
        this.alias = "";
        for (String s : names) {
            if (s.trim().length() > 0) {
                alias += s.trim().substring(0, 1);
            }
        }
        return alias.toUpperCase();
    }

    private String formatHaitianName(String name) {
        String str1 = name.replaceAll(" +", " ").replaceAll(" - |--| -", "-");
        String str2 = str1.replaceAll(" [a-z]|]", " *").replaceAll("-[a-z]", "-*");
        str1 = Character.isUpperCase(str1.charAt(0)) ? str1 : Character.toUpperCase(str1.charAt(0)) + str1.substring(1);
        int i = str2.indexOf("*");
        String fn = "";
        while (i > 0) {
            char a = Character.toUpperCase(str1.charAt(i));
            String s = str2.substring(0, i + 1);
            fn += s.replace('*', a);
            str2 = str2.substring(i + 1);
            str1 = str1.substring(i + 1);
            i = str2.indexOf("*");
        }
        return fn + str1;
    }
}
