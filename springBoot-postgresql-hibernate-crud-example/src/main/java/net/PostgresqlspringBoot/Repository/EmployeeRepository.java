package net.PostgresqlspringBoot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.PostgresqlspringBoot.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
