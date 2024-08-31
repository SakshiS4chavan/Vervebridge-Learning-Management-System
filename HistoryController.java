package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.engine.spi.EffectiveEntityGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.isa.dto.EvaluationKnowledgeDTO;
import rs.ac.singidunum.novisad.isa.dto.FacultyDTO;
import rs.ac.singidunum.novisad.isa.dto.HistoryDTO;
import rs.ac.singidunum.novisad.isa.dto.ProfessorDTO;
import rs.ac.singidunum.novisad.isa.dto.StudentDTO;
import rs.ac.singidunum.novisad.isa.dto.StudentOnTheYearDTO;
import rs.ac.singidunum.novisad.isa.dto.StudentServicesDTO;
import rs.ac.singidunum.novisad.isa.dto.StudentTestsDTO;
import rs.ac.singidunum.novisad.isa.dto.StudyProgrammeDTO;
import rs.ac.singidunum.novisad.isa.dto.SubjectDTO;
import rs.ac.singidunum.novisad.isa.dto.TakingExamDTO;
import rs.ac.singidunum.novisad.isa.dto.UserDTO;
import rs.ac.singidunum.novisad.isa.dto.YearOfStudyDTO;
import rs.ac.singidunum.novisad.isa.model.Role;
import rs.ac.singidunum.novisad.isa.model.StudentHistory;
import rs.ac.singidunum.novisad.isa.model.StudentOnTheYear;
import rs.ac.singidunum.novisad.isa.model.StudentServices;
import rs.ac.singidunum.novisad.isa.model.StudentTests;
import rs.ac.singidunum.novisad.isa.model.StudyProgramme;
import rs.ac.singidunum.novisad.isa.model.TakingExam;
import rs.ac.singidunum.novisad.isa.model.User;
import rs.ac.singidunum.novisad.isa.model.YearOfStudy;
import rs.ac.singidunum.novisad.isa.service.HistoryService;
import rs.ac.singidunum.novisad.isa.service.StudentServiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/history")
public class HistoryController {
	
	 @Autowired
	 HistoryService h;
	 
	 @Autowired
	 StudentServiceService l;
	 
	 @GetMapping("/allhistory")
	 public ResponseEntity<Iterable<HistoryDTO>> getAll() {
		 return null;
	 }
	 
	 @PostMapping("/createHistory")
		//@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<StudentHistory> create(@RequestBody StudentHistory yearOfStudy){
		    try {
		    	h.save(yearOfStudy);
				return new ResponseEntity<StudentHistory>(yearOfStudy, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ResponseEntity<StudentHistory>(HttpStatus.BAD_REQUEST);
		}
	 
	 @PutMapping("/{id}")
		//@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<StudentHistory> update(@PathVariable("id") Long typeRanksId, @RequestBody StudentHistory changedTypeRanks) {
		 StudentHistory typeRanks = h.findOne(typeRanksId).orElse(null);
			if (typeRanks != null) {
				h.save(changedTypeRanks);
				return new ResponseEntity<StudentHistory>(changedTypeRanks, HttpStatus.OK);
			}
			return new ResponseEntity<StudentHistory>(HttpStatus.NOT_FOUND);
		}
	 
	 @DeleteMapping("/{id}")
		//@PreAuthorize("hasRole('ADMINISTRATOR')")
		public ResponseEntity<StudentHistory> deleteKupovina(@PathVariable("id") Long typeRanksId) {
			if (h.findOne(typeRanksId).isPresent()) {
				h.delete(typeRanksId);
				return new ResponseEntity<StudentHistory>(HttpStatus.OK);
			}
			return new ResponseEntity<StudentHistory>(HttpStatus.NOT_FOUND);
		}
	 
}
