package steve.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import steve.blog.core.model.Tag;

interface TagJpaRepository extends JpaRepository<Tag, Integer> {}
