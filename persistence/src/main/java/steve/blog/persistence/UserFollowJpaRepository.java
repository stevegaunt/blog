package steve.blog.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import steve.blog.core.model.User;
import steve.blog.core.model.UserFollow;

interface UserFollowJpaRepository extends JpaRepository<UserFollow, Integer> {
    List<UserFollow> findByFollower(User follower);

    void deleteByFollowerAndFollowing(User follower, User following);

    boolean existsByFollowerAndFollowing(User follower, User following);
}
