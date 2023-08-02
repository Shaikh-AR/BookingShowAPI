package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long> {

}
