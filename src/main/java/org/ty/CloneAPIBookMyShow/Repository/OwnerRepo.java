package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

}
