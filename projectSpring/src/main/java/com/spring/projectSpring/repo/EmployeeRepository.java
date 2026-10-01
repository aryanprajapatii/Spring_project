package com.spring.projectSpring.repo;

import com.spring.projectSpring.entity.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
}
