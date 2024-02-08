package inf.questpartner.repository.studytree;

import inf.questpartner.domain.studytree.StudyTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyTreeRepository extends JpaRepository<StudyTree, Long> {

}
