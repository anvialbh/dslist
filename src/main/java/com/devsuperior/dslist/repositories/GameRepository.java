package com.devsuperior.dslist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;

public interface GameRepository extends JpaRepository<Game, Long> {
	
	String sql = "SELECT g.id"
			        + ", g.title "
			        + ", g.game_year "
			        + ", g.img_url AS imgUrl "
			        + ", g.short_description AS shortDescription "
			        + ", b.position "
			     + "FROM tb_game g "
			     + "JOIN tb_belonging b ON (g.id = b.game_id) "
			    + "WHERE b.list_id = :listId "
			    + "ORDER BY b.position "
	;
	
	@Query(nativeQuery = true, value = sql)
	List<GameMinProjection> searchByList(Long listId);

}
