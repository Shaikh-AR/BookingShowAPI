package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;

public interface ShowRepo extends JpaRepository<MovieShow, Long>{

}
