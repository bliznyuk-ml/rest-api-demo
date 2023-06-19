package org.itstep.studentservice.api;

import lombok.RequiredArgsConstructor;
import org.itstep.studentservice.domain.Student;
import org.itstep.studentservice.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/student/")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "${ui.host}", methods = {
        RequestMethod.GET,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.POST
})
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findById(@PathVariable Integer id) {
        return ResponseEntity.of(studentRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        try {
            var savedStudent = studentRepository.save(student);
            return ResponseEntity
                    .created(URI.create("/api/v1/student/%s".formatted(savedStudent.getId())))
                    .build();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student st = optionalStudent.get();
            if (student.getFirstName() != null) {
                st.setFirstName(student.getFirstName());
            } else {
                st.setFirstName(st.getFirstName());
            }
            if (student.getLastName() != null) {
                st.setLastName(student.getLastName());
            } else {
                st.setLastName(st.getLastName());
            }
            if (student.getBirthday() != null) {
                st.setBirthday(student.getBirthday());
            } else {
                st.setBirthday(st.getBirthday());
            }
            if (student.getPhone() != null) {
                st.setPhone(student.getPhone());
            } else {
                st.setPhone(st.getPhone());
            }
            if (student.getEmail() != null) {
                st.setEmail(student.getEmail());
            } else {
                st.setEmail(st.getEmail());
            }
            studentRepository.save(st);
            return new ResponseEntity<>(st, HttpStatus.OK);
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Student by id %s not found".formatted(id));
        problemDetail.setTitle("Error update student");
        return ResponseEntity.badRequest()
                .body(problemDetail);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student st = optionalStudent.get();
            studentRepository.delete(st);
            return new ResponseEntity<>(st, HttpStatus.OK);
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Student by id %s not found".formatted(id));
        problemDetail.setTitle("Error delete student");
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }

}
