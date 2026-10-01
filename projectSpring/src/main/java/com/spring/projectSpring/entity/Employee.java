package com.spring.projectSpring.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "employee_entries")
@Data
@NoArgsConstructor
public class Employee {


    @Id
    private ObjectId objectId;
    private String city;
    @NonNull
    private String name;
    private String  salary;


}
