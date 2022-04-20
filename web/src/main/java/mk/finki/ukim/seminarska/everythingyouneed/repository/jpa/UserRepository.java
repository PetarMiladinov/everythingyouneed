package mk.finki.ukim.seminarska.everythingyouneed.repository.jpa;

import mk.finki.ukim.seminarska.everythingyouneed.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @EntityGraph(type= EntityGraph.EntityGraphType.FETCH,attributePaths = "carts")
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type= EntityGraph.EntityGraphType.LOAD,attributePaths = {"carts","discount"})
    @Query("select u from User u")
    List<User> loadAll();


  Optional<User> findByUsername(String username);
//    @Query(value = "select * " +
//            "from shop_users u " +
//            "where u.username = :name"
//    ,nativeQuery = true)
//    Optional<User> findByUsername(@Param("name")String txt);
//    @Query("select u.username,u.name,u.surname from User u")
//    List<UserProjection> takeUsernameAndSurenameByProjection();
}
