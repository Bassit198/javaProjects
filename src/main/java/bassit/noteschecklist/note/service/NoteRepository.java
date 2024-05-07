package bassit.noteschecklist.note.service;

import bassit.noteschecklist.note.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
