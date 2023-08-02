package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
