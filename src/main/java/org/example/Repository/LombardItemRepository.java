package org.example.Repository;

import org.example.Model.LombardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LombardItemRepository extends JpaRepository<LombardItem, Long> {
}
