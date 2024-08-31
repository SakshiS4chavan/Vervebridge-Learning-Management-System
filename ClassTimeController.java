package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ClassTimeDTO;
import dto.SubjectDTO;
import dto.SubjectRealizationDTO;
import dto.TopicDTO;
import dto.TypeOfTeachingDTO;
import dto.TypeOfTopicDTO;
import model.ClassTime;
import model.MessageResponse;
import service.ClassTimeService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/classTimes")
public class ClassTimeController {

    private static final Logger logger = LoggerFactory.getLogger(ClassTimeController.class);

    @Autowired
    private ClassTimeService service;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('PROFESSOR')")
    public ResponseEntity<Iterable<ClassTimeDTO>> getAll() {
        ArrayList<ClassTimeDTO> classTimeDTOs = new ArrayList<>();
        for (ClassTime classTime : service.findAll()) {
            Set<TopicDTO> topicsDTO = new HashSet<>();
            for (Topic topic : classTime.getSubjectRealization().getSubject().getTopic()) {
                topicsDTO.add(new TopicDTO(topic.getId(), topic.getOpis(), 
                        new TypeOfTopicDTO(topic.getTopicType().getId(), topic.getTopicType().getNaziv())));
            }
            classTimeDTOs.add(new ClassTimeDTO(classTime.getId(), classTime.getVremePocetka(), 
                    classTime.geAAAARRtVremeKraja(), 
                    new TopicDTO(classTime.getTopic().getId(), classTime.getTopic().getOpis(), 
                            new TypeOfTopicDTO(classTime.getTopic().getTopicType().getId(), 
                            classTime.getTopic().getTopicType().getNaziv())), 
                    new TypeOfTeachingDTO(), 
                    new SubjectRealizationDTO(classTime.getSubjectRealization().getId(), 
                            new SubjectDTO(classTime.getSubjectRealization().getSubject().getId(), 
                            classTime.getSubjectRealization().getSubject().getNaziv(), 
                            classTime.getSubjectRealization().getSubject().getEspb(), 
                            classTime.getSubjectRealization().getSubject().isObavezan(), 
                            classTime.getSubjectRealization().getSubject().getBrojPredavanja(), 
                            classTime.getSubjectRealization().getSubject().getBrojVezbi(), 
                            classTime.getSubjectRealization().getSubject().getDrugiObliciNastave(), 
                            classTime.getSubjectRealization().getSubject().getIstrazivackiRad(), 
                            classTime.getSubjectRealization().getSubject().getOstaliCasovi(), 
                            null, topicsDTO, null))));
        }
        return new ResponseEntity<>(classTimeDTOs, HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    @PreAuthorize("hasRole('PROFESSOR') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<Iterable<ClassTimeDTO>> findByUsername(@PathVariable("username") String username) {
        ArrayList<ClassTimeDTO> classTimeDTOs = new ArrayList<>();
        for (ClassTime classTime : service.findByUsername(username)) {
            Set<TopicDTO> topicsDTO = new HashSet<>();
            for (Topic topic : classTime.getSubjectRealization().getSubject().getTopic()) {
                topicsDTO.add(new TopicDTO(topic.getId(), topic.getOpis(), 
                        new TypeOfTopicDTO(topic.getTopicType().getId(), topic.getTopicType().getNaziv())));
            }
            classTimeDTOs.add(new ClassTimeDTO(classTime.getId(), classTime.getVremePocetka(), 
                    classTime.getVremeKraja(), 
                    new TopicDTO(classTime.getTopic().getId(), classTime.getTopic().getOpis(), 
                            new TypeOfTopicDTO(classTime.getTopic().getTopicType().getId(), 
                            classTime.getTopic().getTopicType().getNaziv())), 
                    new TypeOfTeachingDTO(classTime.getTypeOfTeaching().getId(), 
                            classTime.getTypeOfTeaching().getNaziv()), 
                    new SubjectRealizationDTO(classTime.getSubjectRealization().getId(), 
                            new SubjectDTO(classTime.getSubjectRealization().getSubject().getId(), 
                            classTime.getSubjectRealization().getSubject().getNaziv(), 
                            classTime.getSubjectRealization().getSubject().getEspb(), 
                            classTime.getSubjectRealization().getSubject().isObavezan(), 
                            classTime.getSubjectRealization().getSubject().getBrojPredavanja(), 
                            classTime.getSubjectRealization().getSubject().getBrojVezbi(), 
                            classTime.getSubjectRealization().getSubject().getDrugiObliciNastave(), 
                            classTime.getSubjectRealization().getSubject().getIstrazivackiRad(), 
                            classTime.getSubjectRealization().getSubject().getOstaliCasovi(), 
                            username, topicsDTO, null))));
        }
        return new ResponseEntity<>(classTimeDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('PROFESSOR')")
    public ResponseEntity<ClassTime> create(@RequestBody ClassTime object) {
        try {
            ClassTime savedObject = service.save(object);
            return new ResponseEntity<>(savedObject, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating ClassTime", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('PROFESSOR')")
    public ResponseEntity<ClassTime> update(@PathVariable("id") Long id, @RequestBody ClassTime changedObject) {
        if (service.existsById(id)) {
            ClassTime updatedObject = service.save(changedObject);
            return new ResponseEntity<>(updatedObject, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('PROFESSOR')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (service.existsById(id)) {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/existsBySubjectID/{objectToChangeID}/{id}/{typeTeachingId}")
    @PreAuthorize("hasRole('PROFESSOR') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageResponse> existsBySubjectID(@PathVariable("objectToChangeID") Long objectToChangeID, 
                                                             @PathVariable("id") Long id, 
                                                             @PathVariable("typeTeachingId") Long typeTeachingId) {
        if (service.existsBySubjectID(id, typeTeachingId)) {
            ClassTime objectToChange = service.findOne(objectToChangeID).orElse(null);
            if (objectToChange != null && !objectToChange.getSubjectRealization().getSubject().getId().equals(id)) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Class time already exists with that type teaching for this subject!"));
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Class time already exists with that type teaching for this subject!"));
        }
        return ResponseEntity.ok(new MessageResponse("Free!"));
    }
}
