package io.osnz

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Repository
interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByName(String name);
}

