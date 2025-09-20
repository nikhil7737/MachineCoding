package org.ad.engine.models;


import lombok.*;
import org.ad.engine.models.enums.AdType;
import org.ad.engine.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class User {

    @Setter(AccessLevel.NONE)
    private String id;

    private String name;
    private Integer age;
    private Gender gender;
    private String city;
    private final List<AdType> interests = new ArrayList<>();
}

